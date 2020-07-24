package com.example.jkapplication.data.network.provider

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofit by lazy { makeRetrofit() }

fun makeRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl("http://ec2-13-125-45-221.ap-northeast-2.compute.amazonaws.com:5000/")
    .client(makeHttpClient())
    .addConverterFactory(GsonConverterFactory.create(Gson()))
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//넷웤 반환값을 observable 로 사용할 수 있게 해주는 컨버터
    .build()

fun makeHttpClient() = OkHttpClient.Builder()
    .addInterceptor (makeLoggingInterceptor())
    .build()