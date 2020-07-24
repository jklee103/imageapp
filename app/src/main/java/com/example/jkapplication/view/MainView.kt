package com.example.jkapplication.view

import com.example.jkapplication.model.Monster

interface MainView {
    var refresh:Boolean
    fun show(items: List<Monster>)
    fun showError(error:Throwable)
}