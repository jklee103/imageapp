package com.example.jkapplication.view.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.jkapplication.R
import com.example.jkapplication.model.Monster
import com.example.jkapplication.model.getProgressItem
import com.example.jkapplication.view.fragments.ScrollFragment
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequestBuilder

class ScrollRecyclerAdapter(
    context: Context,
    list: java.util.ArrayList<Monster>,
    loadType: String
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), BaseAdapter {
    var TYPE_ITEM = 0
    var TYPE_LOADING = 1

    val context = context
    val list = list
    var loadType = loadType


    override fun getItemViewType(position: Int): Int {
        return if (list[position].img_url != "progress")
            TYPE_ITEM
        else
            TYPE_LOADING
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_ITEM) {
            var view =
                LayoutInflater.from(parent.context).inflate(R.layout.view_fresco, parent, false)
            var holder = Holder(view)
            holder.image.hierarchy.setPlaceholderImage(R.drawable.view_blank_box)
            return holder
        } else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_progress, parent,false)
            return ProgressHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var params: StaggeredGridLayoutManager.LayoutParams = holder.itemView.getLayoutParams() as StaggeredGridLayoutManager.LayoutParams

        when(holder){
            is Holder -> {
                holder.title.text = list[position].title
                holder.date.text = list[position].date
            }
            is ProgressHolder -> {
                params.isFullSpan= true
            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.v_fresco_tv_title)
        var date: TextView = itemView.findViewById(R.id.v_fresco_tv_date)
        var image: SimpleDraweeView = itemView.findViewById(R.id.v_fresco_iv_image)
    }
    class ProgressHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

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

    fun moreLoad(holder: ScrollRecyclerAdapter.Holder, position: Int) {
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

    fun addprogress(position: Int = list.size) {
        if (position == 0)//topscroll
            list.add(position, getProgressItem())
        else//bottom
            list.add(getProgressItem())
    }

    fun removeprogress(position: Int = list.size - 1) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }
}
