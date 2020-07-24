package com.example.jkapplication.data.network

import com.example.jkapplication.data.network.provider.makeRetrofit

class MainRetrofit {
    var retrofit = makeRetrofit()

    var service = retrofit.create(MonsterApi::class.java)
    var response = service.getImages()


}