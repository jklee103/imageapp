package com.example.jkapplication.presenter

import android.util.Log
import com.example.jkapplication.data.ImagesResponse
import com.example.jkapplication.data.PostImagesResponse
import com.example.jkapplication.data.network.MainRetrofit
import com.example.jkapplication.model.Monster
import com.example.jkapplication.view.adapters.BaseAdapter
import com.example.jkapplication.view.adapters.FrescoRecyclerAdapter
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GlidePresenter(adapter: BaseAdapter, list:ArrayList<Monster>) : BasePresenter() {

    var retrofit = MainRetrofit()
    var imglist = retrofit.response

    var list=list
    var adapter=adapter
    fun connect(){

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
                list = body!!.images
                Log.d("item check", list[0].date)
                adapter.replaceAll(list)
            }

        })
    }
    fun postConnect(checkMonster: HashMap<String, Boolean>){

        var postImgList=retrofit.getPostArgu(checkMonster)
        postImgList.clone().enqueue(object :Callback<PostImagesResponse?>{
            override fun onFailure(call: Call<PostImagesResponse?>, t: Throwable) {
                Log.e("post connect","fail")
            }

            override fun onResponse(
                call: Call<PostImagesResponse?>,
                response: Response<PostImagesResponse?>
            ) {
                val body = response.body()
                Log.d("post connect", "ok")
                list = body!!.data.images
                Log.d("item check", list[0].date)
                adapter.replaceAll(list)
            }

        })
    }

}
