package com.example.jkapplication.presenter

import android.util.Log
import com.example.jkapplication.data.PostImagesResponse
import com.example.jkapplication.model.Monster
import com.example.jkapplication.view.MainView
import com.example.jkapplication.view.adapters.BaseAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostPresenter(val view: MainView) : BasePresenter() {

    fun postConnect(checkMonster: HashMap<String, Boolean>) {

        var postImgList = retrofit.getPostArgu(checkMonster)
        postImgList.clone().enqueue(object : Callback<PostImagesResponse?> {
            override fun onFailure(call: Call<PostImagesResponse?>, t: Throwable) {
                Log.e("post connect", "fail")
            }

            override fun onResponse(
                call: Call<PostImagesResponse?>,
                response: Response<PostImagesResponse?>
            ) {
                val body = response.body()
                Log.d("post connect", "ok")
                view.show(body!!.data.images)
            }

        })
    }


}