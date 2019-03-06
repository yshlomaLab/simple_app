package com.intelity.domain.interactors

import com.intelity.domain.data.ApplicationData
import com.intelity.domain.data.RatingData
import com.intelity.domain.repository.RatingDataRepository
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Get rating data about each application. Also implemented delay because
 * service has quota 5 requests per second.
 */
class GetRatingData() : UseCase<MutableList<RatingData>>() {

    lateinit var repository: RatingDataRepository

    private var applicationDataList: MutableList<ApplicationData>? = null

    @Inject constructor(repository: RatingDataRepository) : this() {
        this.repository = repository
    }

    fun setPackagesList(list: MutableList<ApplicationData>) {
        applicationDataList = list
    }

    override fun buildUseCaseObservable(): Observable<MutableList<RatingData>> {
        return Observable
            .range(0, applicationDataList!!.size)
            .concatMap { i ->
                repository.getRatingForApp(applicationDataList!![i].packageName).
                onExceptionResumeNext(Observable.just(RatingData()))?.
                delay(200, TimeUnit.MILLISECONDS)}
            .buffer(5)
    }
}