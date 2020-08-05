package com.example.jkapplication.presenter

import android.util.Log
import com.example.jkapplication.data.PostImagesResponse
import com.example.jkapplication.model.Monster
import com.example.jkapplication.view.MainView
import com.example.jkapplication.view.adapters.BaseAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MorePresenter (val view: MainView) : BasePresenter(){
    var isLast = false

    fun moreConnect(checkMonster: HashMap<String, Int>, replaceList: Boolean) {

        var postImgList = retrofit.getMoreArgu(checkMonster)
        postImgList.clone().enqueue(object : Callback<PostImagesResponse?> {
            override fun onFailure(call: Call<PostImagesResponse?>, t: Throwable) {
                Log.e("more post connect", "fail")
            }

            override fun onResponse(
                call: Call<PostImagesResponse?>,
                response: Response<PostImagesResponse?>
            ) {
                val body = response.body()
                Log.d("more post connect", "ok")
                view.show(body!!.data.images) //replace, add 처리는 뷰에서
                isLast = body!!.data.isLast
            }

        })
    }
    fun getIsLast(): Boolean {
        return isLast
    }

    fun setIsLast(newlast: Boolean) {
        isLast = newlast
    }
}