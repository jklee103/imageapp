package com.example.jkapplication.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jkapplication.R
import com.example.jkapplication.model.Monster

class PicassoRecyclerAdapter(context: Context, list:ArrayList<Monster>): RecyclerView.Adapter<GlideRecyclerAdapter.Holder>() {//홀더는 같이 써도될거같음
    //어댑터도 같이 써도 될듯.. 나중에 글라이드 쪽만 빼서 손보면될
    val context = context
    val list = list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlideRecyclerAdapter.Holder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.view_glide, parent, false)
        var holder = GlideRecyclerAdapter.Holder(view)
        return holder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: GlideRecyclerAdapter.Holder, position: Int) {
        holder.title.text = list[position].title
        holder.date.text = list[position].date
        Glide.with(holder.image.context).load(list[position].img_url).override(500,500).centerInside().into(holder.image)

    }

}