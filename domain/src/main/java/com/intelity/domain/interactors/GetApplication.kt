package com.intelity.domain.interactors

import com.intelity.domain.data.ApplicationData
import com.intelity.domain.repository.AppDataRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetApplication() : UseCase<ApplicationData>() {

    lateinit var repository : AppDataRepository

    private var packageName : String? = null

    @Inject
    constructor(repository: AppDataRepository) : this() {
        this.repository = repository
    }

    fun setPackageName(name : String?) {
        packageName = name
    }

    override fun buildUseCaseObservable(): Observable<ApplicationData> {
        return repository.getApplicationByPackageName(packageName!!)
    }
}