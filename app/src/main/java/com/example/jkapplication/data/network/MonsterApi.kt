package com.example.jkapplication.data.network

import com.example.jkapplication.data.ImagesResponse
import com.example.jkapplication.data.PostImagesResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface MonsterApi {
    @GET("images")
    fun getImages():Call<ImagesResponse?>

    @Headers("Content-Type: application/json")
    @POST("images")
    fun getPostImages(
        @Body body:HashMap<String,Boolean>
    ):Call<PostImagesResponse?>

}