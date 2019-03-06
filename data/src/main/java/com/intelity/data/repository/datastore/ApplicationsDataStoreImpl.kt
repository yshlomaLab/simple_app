package com.intelity.data.repository.datastore

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.intelity.data.entity.ApplicationEntity
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Application Data Store implementation with logic
 * to retrieve apps and injected package manager.
 */
@Singleton
class ApplicationsDataStoreImpl : ApplicationsDataStore {

    private val packageManager: PackageManager

    @Inject constructor(packageManager: PackageManager) {
        this.packageManager = packageManager
    }

    override fun getApplicationsList(): Observable<MutableList<ApplicationEntity>> {
        val instaledApplications = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
            .filter { applicationInfo ->
                ((applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM) == 0)and
                        (!applicationInfo.packageName.startsWith("com.android")) and
                        (!applicationInfo.packageName.startsWith("com.example.android"))}

        val resultList = mutableListOf<ApplicationEntity>()
        for (info in instaledApplications) {
            val name = info.name ?: info.packageName
            val newEntry = ApplicationEntity(name, info.packageName)
            newEntry.icon = packageManager.getApplicationIcon(info.packageName)
            newEntry.launchIntent = packageManager.getLaunchIntentForPackage(info.packageName)
            resultList.add(newEntry)
        }
        return Observable.just(resultList)
    }

    override fun getApplicationByPackageName(packageName: String): Observable<ApplicationEntity> {
        val instaledApplication = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
            .filter { applicationInfo -> (applicationInfo.packageName == packageName)
                ((applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM) == 0)and
                        (!applicationInfo.packageName.startsWith("com.example"))}.first()
        if (instaledApplication != null) {
            val name = instaledApplication.name ?: instaledApplication.packageName
            val newEntry = ApplicationEntity(name, instaledApplication.packageName)
            newEntry.icon = packageManager.getApplicationIcon(instaledApplication.packageName)
            newEntry.launchIntent = packageManager.getLaunchIntentForPackage(instaledApplication.packageName)
            return Observable.just(newEntry)
        }
        return Observable.just(null)
    }

}