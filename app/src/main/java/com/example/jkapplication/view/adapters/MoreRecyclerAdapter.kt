package com.example.jkapplication.view.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jkapplication.R
import com.example.jkapplication.model.Monster
import com.facebook.drawee.view.SimpleDraweeView
import java.util.ArrayList
import kotlin.collections.ArrayList

class MoreRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), BaseAdapter {
    private val TYPE_ITEM = 1
    private val TYPE_FOOTER = 2

    private var tasks: ArrayList<Monster> = arrayListOf()

    private fun ViewGroup.inflate(layoutRes: Int): View =
        LayoutInflater.from(context).inflate(layoutRes, this, false)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_FOOTER -> FooterViewHolder(parent.inflate(R.layout.item_footer))
            else -> Holder(parent.inflate(R.layout.view_fresco))
        }

    }

    override fun getItemCount(): Int {
        return tasks.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FooterViewHolder -> {
                holder.itemView.setOnClickListener {

                }
            }
            is Holder -> {
                holder.title.text = list[position].title
                holder.date.text = list[position].date
                frescoLoad(holder, position)
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


    class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
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

    override fun replaceAll(list: ArrayList<Monster>) {
        TODO("Not yet implemented")
    }
}
