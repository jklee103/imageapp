package com.example.jkapplication.presenter

import android.util.Log
import com.example.jkapplication.data.ImagesResponse
import com.example.jkapplication.data.PostImagesResponse
import com.example.jkapplication.data.network.MainRetrofit
import com.example.jkapplication.model.Monster
import com.example.jkapplication.view.MainView
import com.example.jkapplication.view.adapters.BaseAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GlidePresenter(val view: MainView) : BasePresenter() {

    fun connect() {
        imglist.clone().enqueue(object : Callback<ImagesResponse?> {
            override fun onFailure(call: Call<ImagesResponse?>, t: Throwable) {
                Log.e("glide fragment", "fail")
            }
            override fun onResponse(
                call: Call<ImagesResponse?>,
                response: Response<ImagesResponse?>
            ) {
                val body = response.body()
                Log.d("glide fragment", "ok")
                view.show(body!!.images)
            }
        })
    }



}
