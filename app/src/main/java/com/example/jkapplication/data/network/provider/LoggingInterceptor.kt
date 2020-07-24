package com.example.jkapplication.data.network.provider

import com.example.jkapplication.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor

fun makeLoggingInterceptor() = HttpLoggingInterceptor().apply {
    level = if(BuildConfig.DEBUG)   HttpLoggingInterceptor.Level.BODY
    else HttpLoggingInterceptor.Level.NONE
}