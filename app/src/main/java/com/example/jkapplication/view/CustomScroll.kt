package com.example.jkapplication.view

import android.util.Log
import androidx.recyclerview.widget.RecyclerView

class CustomScroll(private val listener:onLoadMore):RecyclerView.OnScrollListener(){
    var loading=false
    //1->bottom, -1->top

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        if (!loading && !recyclerView.canScrollVertically(1)) {
            Log.i("onScrollStateChanged", "End of list arrived")
            listener?.onLoadMore()
            loading = true
        }
        // 이 외의 경우(not bottom) POSITION은 IDLE 상태
        else {
            Log.i("onScrollStateChanged", "Idle")
        }
    }
    interface onLoadMore{
        fun onLoadMore()
    }
    fun setLoaded() {
        loading = false
    }

}