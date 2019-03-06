package com.intelity.domain.repository

import com.intelity.domain.data.RatingData
import io.reactivex.Observable

/**
 * Interface to retrieve mapped rating data.
 */
interface RatingDataRepository {
    fun getRatingForApp(pakageName : String?) : Observable<RatingData>
}