package com.example.jkapplication.data

import com.example.jkapplication.model.Monster

data class ImagesResponse(
    var images: ArrayList<Monster>,
    var isLast:Boolean,
    var page:Int
)