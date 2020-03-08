package com.rodrigotristany.mrjeff.domain.base

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class UseCase<T, in Params>(private val subscribeScheduler: Scheduler,
                                     private val postExecutionScheduler: Scheduler) {

    private val disposables: CompositeDisposable = CompositeDisposable()

    abstract fun buildUseCaseSingle(params: Params?): Observable<T>

    fun execute(observer: Observer<T>, params: Params? = null) {
        val observable: Observable<T> = this.buildUseCaseSingle(params)
            .subscribeOn(subscribeScheduler)
            .observeOn(postExecutionScheduler)
        (observable.subscribeWith(observer) as? Disposable)?.let {
            disposables.add(it)
        }
    }

    fun dispose() {
        disposables.clear()
    }
}