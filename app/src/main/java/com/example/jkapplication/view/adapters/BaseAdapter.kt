package com.example.jkapplication.view.adapters

import com.example.jkapplication.model.Monster

interface BaseAdapter {
    fun glideLoad(holder: GlideRecyclerAdapter.Holder, position: Int)
    fun picassoLoad(holder: GlideRecyclerAdapter.Holder, position: Int)
    fun frescoLoad(holder: FrescoRecyclerAdapter.Holder, position: Int)
    fun replaceAll(list: ArrayList<Monster>)
    fun addAll(list: ArrayList<Monster>)

}