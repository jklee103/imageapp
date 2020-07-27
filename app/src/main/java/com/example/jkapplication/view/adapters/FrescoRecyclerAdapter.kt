package com.example.jkapplication.view.adapters

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jkapplication.R
import com.example.jkapplication.model.Monster
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.common.ResizeOptions
import com.facebook.imagepipeline.request.ImageRequestBuilder
import java.net.URL
import java.util.*


class FrescoRecyclerAdapter(context: Context, list: ArrayList<Monster>, loadType: String) :
    RecyclerView.Adapter<FrescoRecyclerAdapter.Holder>(), BaseAdapter {
    val context = context
    val list = list
    var loadType = loadType


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.view_fresco, parent, false)
        var holder = Holder(view)
        holder.image.hierarchy.setPlaceholderImage(R.drawable.view_blank_box)
        return holder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.title.text = list[position].title
        holder.date.text = list[position].date
        frescoLoad(holder, position)
    }

    override fun glideLoad(holder: GlideRecyclerAdapter.Holder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun picassoLoad(holder: GlideRecyclerAdapter.Holder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun frescoLoad(holder: Holder, position: Int) {
        val imageRequest =
            ImageRequestBuilder.newBuilderWithSource(Uri.parse(list[position].img_url))
                //.setResizeOptions(ResizeOptions(300,300))
                .build()
        holder.image.setImageRequest(imageRequest)
        holder.image.aspectRatio = (list[position].width.toFloat())/(list[position].height.toFloat()) //비율 설정해줘야 높이 fit됨

    }

    override fun replaceAll(items: ArrayList<Monster>) {
        list.apply {
            clear()
            addAll(items)
            notifyDataSetChanged()
        }
    }
    fun addAll(items: ArrayList<Monster>) {
        list.apply {
            addAll(items)
            notifyDataSetChanged()
        }
    }
    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.v_fresco_tv_title)
        var date: TextView = itemView.findViewById(R.id.v_fresco_tv_date)
        var image: SimpleDraweeView = itemView.findViewById(R.id.v_fresco_iv_image)

    }
}