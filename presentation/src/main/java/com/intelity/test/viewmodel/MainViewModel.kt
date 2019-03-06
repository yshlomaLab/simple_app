package com.intelity.test.viewmodel

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.intelity.domain.data.ApplicationData
import com.intelity.domain.data.RatingData
import com.intelity.domain.interactors.GetApplication
import com.intelity.domain.interactors.GetApplicationRating
import com.intelity.domain.interactors.GetApplicationsData
import com.intelity.domain.interactors.GetRatingData
import com.intelity.test.Const
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject


/**
 * Base logic to manipulate view and notify about changes.
 */
class MainViewModel(application: Application) : BaseViewModel(application) {

    @Inject
    lateinit var sharedPrefs : SharedPreferences

    @Inject
    lateinit var getApplicationsData: GetApplicationsData

    @Inject
    lateinit var getRatingData: GetRatingData

    @Inject
    lateinit var getApplication: GetApplication

    @Inject
    lateinit var getApplicationRating: GetApplicationRating

    private var appList = MutableLiveData<MutableList<ApplicationData>>()

    init {
        loadApplicationList()
    }

    private fun loadApplicationList() {
        getApplicationsData.execute(object : DisposableObserver<MutableList<ApplicationData>>() {
            override fun onNext(data: MutableList<ApplicationData>) {
                appList.value = data.sortedBy { it.title }.toMutableList()
            }

            override fun onComplete() {
                loadRatingData()
            }

            override fun onError(e: Throwable) {
            }
        })
    }

    private fun loadRatingData() {
        getRatingData.setPackagesList(appList.value!!)
        getRatingData.execute(object : DisposableObserver<MutableList<RatingData>>() {
            override fun onNext(ratingList: MutableList<RatingData>) {
                if (!appList.value.isNullOrEmpty()) {
                    for (appData in appList.value!!) {
                        for (ratingData in ratingList) {
                            if (appData.packageName.equals(ratingData.packageName)) {
                                appData.ratingData = ratingData
                            }
                        }
                        appList.postValue(appList.value)
                    }
                }
            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
            }
        })
    }

    private fun getInstalledApplication(packageName: String) {
        getApplicationRating.setPackageName(packageName)
        getApplication.setPackageName(packageName)
        getApplication.execute(object : DisposableObserver<ApplicationData>() {
            override fun onNext(application: ApplicationData) {
                appList.value?.add(application)
                appList.value?.sortedBy { it.title }?.toMutableList()
                appList.postValue(appList.value)
            }

            override fun onComplete() {
                getInstalledApplicationRating()
            }

            override fun onError(e: Throwable) {
            }
        })
    }

    private fun getInstalledApplicationRating() {
        getApplicationRating.execute(object : DisposableObserver<RatingData>() {
            override fun onNext(rating: RatingData) {
                if (!appList.value.isNullOrEmpty()) {
                    for (appData in appList.value!!) {
                        if (appData.packageName.equals(rating.packageName)) {
                            appData.ratingData = rating
                        }
                        appList.postValue(appList.value)
                    }
                }
            }
            override fun onComplete() {
            }
            override fun onError(e: Throwable) {
            }
        })
    }

    private fun removeUninstalledApplication(packageName: String) {
        if (!appList.value.isNullOrEmpty()) {
            var appToRemove : ApplicationData? = null
            for (appData in appList.value!!) {
                if (appData.packageName.equals(packageName)) {
                    appToRemove = appData
                }
            }
            if (appToRemove != null) {
                appList.value?.remove(appToRemove)
                appList.postValue(appList.value)
            }
        }
    }

    fun onResume() {
        val removedSet = sharedPrefs.getStringSet(Const.PACKAGE_REMOVED, mutableSetOf())
        val addedSet = sharedPrefs.getStringSet(Const.PACKAGE_ADDED, mutableSetOf())
        if (appList.value != null) {
            removedSet?.forEach{name -> removeUninstalledApplication(name)}
            addedSet?.forEach { name -> getInstalledApplication(name) }
        }
    }

    fun getAppLiveData(): MutableLiveData<MutableList<ApplicationData>> {
        return this.appList
    }

    override fun onCleared() {
        super.onCleared()
        getApplicationsData.dispose()
        getRatingData.dispose()
    }
}