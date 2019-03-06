package com.intelity.data.repository.datastore

import com.intelity.data.api.LookupService
import com.intelity.data.entity.RatingEntity
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RatingDataStoreImpl : RatingDataStore {

    private val api : LookupService
    private val accessToken : String

    @Inject constructor(service : LookupService, accessToken : String) {
        this.api = service
        this.accessToken = accessToken
    }

    override fun getRatingForApp(pakageName: String?): Observable<RatingEntity> {
        return if (pakageName != null)
            api.getAppData(pakageName, accessToken)
        else Observable.just(null)
    }
}