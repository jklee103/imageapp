package com.example.jkapplication.presenter

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter {
    protected var subscriptions = CompositeDisposable()
    fun onViewDestroyed(){
        subscriptions.dispose()
    }

}