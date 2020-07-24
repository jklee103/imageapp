package com.example.jkapplication.data.network

import com.example.jkapplication.data.ImagesResponse
import com.example.jkapplication.data.network.provider.makeRetrofit
import com.example.jkapplication.model.Monster
import io.reactivex.*

class GlideRetrofit {
    var retrofit = makeRetrofit()

    var service = retrofit.create(MonsterApi::class.java)
    var response = service.getImages()


}