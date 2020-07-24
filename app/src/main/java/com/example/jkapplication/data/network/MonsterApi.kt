package com.example.jkapplication.data.network

import com.example.jkapplication.data.ImagesResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface MonsterApi {
    @GET("images")
    fun getImages():Call<ImagesResponse?>
}