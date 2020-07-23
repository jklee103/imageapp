package com.example.jkapplication.network

import retrofit2.http.GET

interface MonsterApi {
    @GET("images")
    fun getImages()
}