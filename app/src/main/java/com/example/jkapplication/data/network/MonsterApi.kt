package com.example.jkapplication.data.network

import com.example.jkapplication.data.ImagesResponse
import com.example.jkapplication.data.PostImagesResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface MonsterApi {
    @GET("images")
    fun getImages():Call<ImagesResponse?>

    @Headers("Content-Type: application/json")
    @POST("images")
    fun getPostImages(
        @Body body:HashMap<String,Boolean>
    ):Call<PostImagesResponse?>

    @Headers("Content-Type: application/json")
    @POST("imagelist")
    fun getMoreImages(
        @Body body:HashMap<String,Int>
    ):Call<PostImagesResponse?>

    @GET("images")
    fun getImages2(): Single<ImagesResponse?>

    @Headers("Content-Type: application/json")
    @POST("images")
    fun getPostImages2(
        @Body body:HashMap<String,Boolean>
    ):Single<PostImagesResponse?>

    @Headers("Content-Type: application/json")
    @POST("imagelist")
    fun getMoreImages2(
        @Body body:HashMap<String,Int>
    ):Single<PostImagesResponse?>

}