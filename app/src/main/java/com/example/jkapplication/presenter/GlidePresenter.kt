package com.example.jkapplication.presenter

import android.util.Log
import com.example.jkapplication.data.ImagesResponse
import com.example.jkapplication.data.applySchedulers
import com.example.jkapplication.data.subscribeBy
import com.example.jkapplication.view.MainView
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GlidePresenter(val view: MainView) : BasePresenter() {
    var imglist = retrofit.response
    fun connect2() {
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
    fun connect(){
        retrofit.service.getImages2()
            .applySchedulers()
            .subscribeBy {
                view.show(it!!.images)
            }
    }


}
