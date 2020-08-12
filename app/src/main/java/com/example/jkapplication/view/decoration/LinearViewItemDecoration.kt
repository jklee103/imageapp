package com.example.jkapplication.view.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class LinearViewItemDecoration: RecyclerView.ItemDecoration() {
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
            outRect.bottom = 40
            outRect.top = 40
            when (type) {
                TYPE_ITEM ->{
                    outRect.left = 40
                    outRect.right = 40
                }
            }
        }
    }
}