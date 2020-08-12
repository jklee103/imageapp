package com.example.jkapplication.view

import com.example.jkapplication.model.Monster

interface MainView {
    fun show(items: ArrayList<Monster>)
    fun showError(error:Throwable)
}