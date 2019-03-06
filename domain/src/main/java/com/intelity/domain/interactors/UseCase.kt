package com.intelity.domain.interactors

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * Base use case which decorated with subscribeOn and observeOn.
 * Also each use case have @link{CompositeDisposable} to prevent memory leaks after
 * activity will finished.
 */
abstract class UseCase<T> internal constructor() {

    private val disposable: CompositeDisposable = CompositeDisposable()

    fun execute(observer: DisposableObserver<T>) {
        val observable = this.buildUseCaseObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        addDisposable(observable.subscribeWith(observer))
    }

    internal abstract fun buildUseCaseObservable(): Observable<T>

    fun dispose() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }

    private fun addDisposable(disposable: Disposable) {
        this.disposable.add(disposable)
    }

}