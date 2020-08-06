package com.example.jkapplication.data

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

//일반적인 스케줄러는 최상위 확장함수에서 추출
fun <T> Single<T>.applySchedulers(): Single<T> = this
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())

//subscribe는 호출 세부사항을 알아볼 수가 없어서 따로 메소드 정의
fun <T> Single<T>.subscribeBy(
    onError: ((Throwable) ->Unit)? = null,
    onSuccess: (T) ->Unit
):Disposable =subscribe(onSuccess,{onError?.invoke(it)})

//새 구독을 편하게 추가하려고 더하기 할당연산자 추가
operator fun CompositeDisposable.plusAssign(disposable: Disposable){
    add(disposable)
}