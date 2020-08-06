package com.example.jkapplication.data.network

import com.example.jkapplication.data.PostImagesResponse
import com.example.jkapplication.data.network.provider.makeRetrofit
import io.reactivex.Single
import retrofit2.Call

class MainRetrofit {

    var retrofit = makeRetrofit()

    var service = retrofit.create(MonsterApi::class.java)
    var response = service.getImages()


    fun getPostArgu(checkMonster: HashMap<String, Boolean>): Call<PostImagesResponse?> {
        var postResponse = service.getPostImages(checkMonster)
        return postResponse
    }

    fun getMoreArgu(page: HashMap<String, Int>): Call<PostImagesResponse?> {
        var postResponse = service.getMoreImages(page)
        return postResponse
    }
    fun getPostArgu2(checkMonster: HashMap<String, Boolean>): Single<PostImagesResponse?> {
        var postResponse = service.getPostImages2(checkMonster)
        return postResponse
    }
    fun getMoreArgu2(page: HashMap<String, Int>): Single<PostImagesResponse?> {
        var postResponse = service.getMoreImages2(page)
        return postResponse
    }

}