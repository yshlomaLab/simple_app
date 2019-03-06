package com.intelity.data.repository.datastore

import com.intelity.data.entity.RatingEntity
import io.reactivex.Observable

interface RatingDataStore {
    fun getRatingForApp(pakageName : String?) : Observable<RatingEntity>
}