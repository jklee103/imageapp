package com.example.jkapplication.view.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jkapplication.R
import com.example.jkapplication.model.Monster
import com.example.jkapplication.view.fragments.ScrollFragment
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequestBuilder

class ScrollRecyclerAdapter(
    context: Context,
    list: java.util.ArrayList<Monster>,
    loadType: String
) :
    RecyclerView.Adapter<ScrollRecyclerAdapter.Holder>(), BaseAdapter {
    private val TYPE_ITEM = 1
    private val TYPE_FOOTER = 2

    val context = context
    val list = list
    var loadType = loadType


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_fresco, parent, false)
        var holder = Holder(view)
        holder.image.hierarchy.setPlaceholderImage(R.drawable.view_blank_box)
        return holder

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.title.text = list[position].title
        holder.date.text = list[position].date
        moreLoad(holder, position)

    }

    override fun getItemCount(): Int {
        return list.size
    }


//    override fun getItemViewType(position: Int)
//            : Int {
//        return when (position) {
//            itemCount - 1 -> TYPE_FOOTER
//            else -> TYPE_ITEM
//        }
//
//    }


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


}
