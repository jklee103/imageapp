package com.example.jkapplication.presenter

import com.example.jkapplication.data.network.MainRetrofit
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter {
    var retrofit = MainRetrofit()



    protected var subscriptions = CompositeDisposable()
    fun onViewDestroyed(){
        subscriptions.dispose()
    }

}