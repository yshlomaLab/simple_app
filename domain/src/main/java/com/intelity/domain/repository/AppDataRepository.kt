package com.intelity.domain.repository

import com.intelity.domain.data.ApplicationData
import io.reactivex.Observable

/**
 * Interface to retrieve mapped application data.
 */
interface AppDataRepository {
    fun getApplicationsList() : Observable<MutableList<ApplicationData>>
    fun getApplicationByPackageName(packageName : String) : Observable<ApplicationData>
}