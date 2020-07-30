package com.example.jkapplication.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jkapplication.R
import com.example.jkapplication.model.Monster
import com.squareup.picasso.Picasso

class GlideRecyclerAdapter(context: Context, list: ArrayList<Monster>, loadType: String) :
    RecyclerView.Adapter<GlideRecyclerAdapter.Holder>(), BaseAdapter {
    val context = context
    val list = list
    var loadType = loadType


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlideRecyclerAdapter.Holder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.view_glide, parent, false)
        var holder = Holder(view)
        return holder
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.title.text = list[position].title
        holder.date.text = list[position].date
        //여기서 라이브러리 구분
        when (loadType) {
            "glide" -> glideLoad(holder as Holder, position)
            "picasso" -> picassoLoad(holder = holder as Holder, position = position)
        }
    }

    override fun glideLoad(holder: Holder, position: Int) {
        Glide.with(holder.image.context).load(list[position].img_url).override(500, 500)
            .centerInside().into(holder.image)

    }

    override fun picassoLoad(holder: Holder, position: Int) {
        Picasso.get().load(list[position].img_url).placeholder(R.drawable.view_blank_box).fit()
            .into(holder.image)
    }

    override fun frescoLoad(holder: FrescoRecyclerAdapter.Holder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun replaceAll(items: ArrayList<Monster>) {
        list.apply {
            clear()
            addAll(items)
            notifyDataSetChanged()
        }
    }

    override fun addAll(items: ArrayList<Monster>) {
        list.apply {
            addAll(items)
            notifyDataSetChanged()
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.v_glide_tv_title)
        var date: TextView = itemView.findViewById(R.id.v_glide_tv_date)
        var image: ImageView = itemView.findViewById(R.id.v_glide_iv_image)
    }


}
