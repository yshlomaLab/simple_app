package com.intelity.domain.interactors

import com.intelity.domain.data.ApplicationData
import com.intelity.domain.repository.AppDataRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Retrieve applications list.
 */
class GetApplicationsData() : UseCase<MutableList<ApplicationData>>() {

    lateinit var repository : AppDataRepository

    @Inject constructor(repository: AppDataRepository) : this() {
        this.repository = repository
    }

    override fun buildUseCaseObservable(): Observable<MutableList<ApplicationData>> {
        return repository.getApplicationsList()
    }

}