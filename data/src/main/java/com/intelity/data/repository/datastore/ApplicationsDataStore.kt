package com.intelity.data.repository.datastore

import com.intelity.data.entity.ApplicationEntity
import io.reactivex.Observable

interface ApplicationsDataStore {
        fun getApplicationsList() : Observable<MutableList<ApplicationEntity>>
        fun getApplicationByPackageName(packageName: String) : Observable<ApplicationEntity>
}