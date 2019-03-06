package com.intelity.data.repository

import com.intelity.data.entity.mapper.ApplicationMapper
import com.intelity.data.repository.datastore.ApplicationsDataStore
import com.intelity.domain.data.ApplicationData
import com.intelity.domain.repository.AppDataRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataRepositoryImpl : AppDataRepository {

    private val dataStore : ApplicationsDataStore
    private val mapper : ApplicationMapper

    @Inject constructor(store: ApplicationsDataStore, mapper : ApplicationMapper) {
        this.dataStore = store
        this.mapper = mapper
    }

    override fun getApplicationsList(): Observable<MutableList<ApplicationData>> {
        return dataStore.getApplicationsList().map(mapper::transformList)
    }

    override fun getApplicationByPackageName(packageName: String): Observable<ApplicationData> {
       return dataStore.getApplicationByPackageName(packageName).map { item -> mapper.transform(item) }
    }
}