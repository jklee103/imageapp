package com.example.jkapplication.view.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class GridViewItemDecoration : RecyclerView.ItemDecoration(){
    var TYPE_ITEM = 0

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        var position = parent.getChildAdapterPosition(view)
        var layoutParams = view.layoutParams as RecyclerView.LayoutParams
        if(position>=0) {
            var type = parent.adapter!!.getItemViewType(position) //getAdapter 되는지 확인
            outRect.bottom = 20
            outRect.top = 20
            when (type) {
                TYPE_ITEM -> if (position % 2 == 0) {
                    outRect.left = 40
                    outRect.right = 20
                } else {
                    outRect.left = 20
                    outRect.right = 40
                }
            }
        }
    }
}