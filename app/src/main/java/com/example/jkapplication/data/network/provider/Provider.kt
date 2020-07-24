package com.example.jkapplication.data.network.provider

abstract class Provider<T> {
    abstract fun creator(): T
    private val instance: T by lazy { creator() }
    var testingInstance:T?=null
    fun get(): T =testingInstance ?: instance
}