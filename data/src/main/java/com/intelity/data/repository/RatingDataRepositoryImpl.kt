package com.intelity.data.repository

import com.intelity.data.entity.mapper.RatingMapper
import com.intelity.data.repository.datastore.RatingDataStore
import com.intelity.domain.data.RatingData
import com.intelity.domain.repository.RatingDataRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RatingDataRepositoryImpl : RatingDataRepository {

    private val mapper : RatingMapper
    private val dataStore : RatingDataStore

    @Inject constructor(store : RatingDataStore, mapper: RatingMapper) {
        this.dataStore = store
        this.mapper = mapper
    }

    override fun getRatingForApp(pakageName: String?): Observable<RatingData> {
        return dataStore.getRatingForApp(pakageName)?.map(mapper::transform)
    }
}