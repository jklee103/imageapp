package com.example.jkapplication.data.network

import com.example.jkapplication.data.ImagesResponse
import com.example.jkapplication.data.PostImagesResponse
import com.example.jkapplication.data.network.provider.makeRetrofit
import com.example.jkapplication.model.Monster
import retrofit2.Call

class MainRetrofit {

    var retrofit = makeRetrofit()

    var service = retrofit.create(MonsterApi::class.java)
    var response = service.getImages()


    fun getPostArgu(checkMonster: HashMap<String,Boolean>): Call<PostImagesResponse?> {
        var postResponse = service.getPostImages(checkMonster)
        return postResponse
    }
}