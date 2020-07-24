package com.example.jkapplication.view.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jkapplication.R
import com.example.jkapplication.model.Monster
import com.example.jkapplication.view.fragments.GlideFragment

class GlideRecyclerAdapter(context: Context, list: ArrayList<Monster>) :
    RecyclerView.Adapter<GlideRecyclerAdapter.Holder>() {
    val context = context
    val list = list

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
        Glide.with(holder.image.context).load(list[position].img_url).override(500,500).centerInside().into(holder.image)

    }

    fun replaceAll(items: ArrayList<Monster>) {
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
        var title: TextView = itemView.findViewById(R.id.v_glide_tv_title)
        var date: TextView = itemView.findViewById(R.id.v_glide_tv_date)
        var image: ImageView = itemView.findViewById(R.id.v_glide_iv_image)
    }



}
