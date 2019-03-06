package com.intelity.data.api

import com.intelity.data.entity.RatingEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Service to retrive information about application.
 */
interface LookupService {

    @GET("api/v2.0/android/apps/lookup.json")
    fun getAppData(@Query("p") packageName : String, @Query("access_token") accessToken : String) : Observable<RatingEntity>

}