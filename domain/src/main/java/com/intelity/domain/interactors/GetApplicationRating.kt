package com.intelity.domain.interactors

import com.intelity.domain.data.RatingData
import com.intelity.domain.repository.RatingDataRepository
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Get application rating with delay to prevent quota.
 */
class GetApplicationRating() : UseCase<RatingData>() {

    lateinit var repository: RatingDataRepository

    private var packageName: String? = null

    @Inject
    constructor(repository: RatingDataRepository) : this() {
        this.repository = repository
    }

    fun setPackageName(name: String) {
        packageName = name
    }
    override fun buildUseCaseObservable(): Observable<RatingData> {
       return repository.getRatingForApp(packageName!!).delay(200, TimeUnit.MILLISECONDS)
    }
}