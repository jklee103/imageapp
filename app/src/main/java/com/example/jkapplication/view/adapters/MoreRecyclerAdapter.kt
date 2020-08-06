package com.example.jkapplication.view.adapters

import android.net.Uri
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.jkapplication.R
import com.example.jkapplication.model.Monster
import com.example.jkapplication.view.fragments.MoreFragment
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequestBuilder

class MoreRecyclerAdapter(list: java.util.ArrayList<Monster>, loadType: String) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), BaseAdapter {
    private val TYPE_ITEM = 0
    private val TYPE_FOOTER = 1
    private val TYPE_LOADING = 0

    val list = list
    var loadType = loadType
    lateinit var viewGroup: ViewGroup
    var mainHandler = Handler()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        viewGroup = parent
        return when (viewType) {
            TYPE_FOOTER -> {
                FooterViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_footer, parent, false)
                )
            }
            else -> {
                var view =
                    LayoutInflater.from(parent.context).inflate(R.layout.view_fresco, parent, false)
                var holder = Holder(view)
                holder.image.hierarchy.setPlaceholderImage(R.drawable.view_blank_box)
                return holder
            }
        }

    }

    override fun getItemCount(): Int {
        return list.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var params: StaggeredGridLayoutManager.LayoutParams =
            holder.itemView.getLayoutParams() as StaggeredGridLayoutManager.LayoutParams


        when (holder) {
            is FooterViewHolder -> {
                params.isFullSpan = true //  이거하면 얘만 너비꽉차게 보

                if (MoreFragment.getInstance().checkLast()) {
                    holder.more.visibility = View.INVISIBLE
                    //Toast.makeText(context, "List End", Toast.LENGTH_SHORT).show()
                } else holder.more.visibility = View.VISIBLE
                holder.more.setOnClickListener {
                    Log.d("btn", "clicked")
                    holder.more.visibility = View.INVISIBLE
                    holder.progress.visibility = View.VISIBLE
                    mainHandler.postDelayed({
                        holder.progress.visibility = View.GONE
                        MoreFragment.getInstance().onLoadMore()

                    }, 2000)

                }
            }
            is Holder -> {
                holder.title.text = list[position].title
                holder.date.text = list[position].date
                moreLoad(holder, position)
            }
        }
    }

    override fun getItemViewType(position: Int)
            : Int {
        return when (position) {
            itemCount - 1 -> TYPE_FOOTER
            else -> TYPE_ITEM
        }

    }

    class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var more: Button = itemView.findViewById(R.id.i_footer_btn_more)
        var progress: ProgressBar = itemView.findViewById(R.id.i_footer_pb_progress)

    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.v_fresco_tv_title)
        var date: TextView = itemView.findViewById(R.id.v_fresco_tv_date)
        var image: SimpleDraweeView = itemView.findViewById(R.id.v_fresco_iv_image)

    }

    override fun glideLoad(holder: GlideRecyclerAdapter.Holder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun picassoLoad(holder: GlideRecyclerAdapter.Holder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun frescoLoad(holder: FrescoRecyclerAdapter.Holder, position: Int) {
        TODO("Not yet implemented")
    }

    fun moreLoad(holder: MoreRecyclerAdapter.Holder, position: Int) {
        val imageRequest =
            ImageRequestBuilder.newBuilderWithSource(Uri.parse(list[position].img_url))
                .build()
        holder.image.setImageRequest(imageRequest)
        holder.image.aspectRatio =
            (list[position].width.toFloat()) / (list[position].height.toFloat()) //비율 설정해줘야 높이 fit됨

    }

    override fun addAll(items: java.util.ArrayList<Monster>) {
        list.apply {
            addAll(items)
            notifyDataSetChanged()
        }
    }

    override fun replaceAll(items: ArrayList<Monster>) {
        list.apply {
            clear()
            addAll(items)
            notifyDataSetChanged()
        }
    }

}
