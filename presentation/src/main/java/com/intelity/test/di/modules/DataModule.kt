package com.intelity.test.di.modules

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import com.intelity.data.api.LookupService
import com.intelity.data.entity.mapper.ApplicationMapper
import com.intelity.data.entity.mapper.RatingMapper
import com.intelity.data.repository.AppDataRepositoryImpl
import com.intelity.data.repository.RatingDataRepositoryImpl
import com.intelity.data.repository.datastore.*
import com.intelity.domain.repository.AppDataRepository
import com.intelity.domain.repository.RatingDataRepository
import com.intelity.test.Const
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Data module to provide all dependecies for the app.
 */
@Module
class DataModule(private val context : Context) {

    @Singleton
    @Provides
    fun providePackageManager() : PackageManager {
        return context.packageManager
    }

    @Singleton
    @Provides
    fun provideSharedPreference() : SharedPreferences {
        return context.getSharedPreferences(Const.SHARED_PREFS, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideApplicationDataStore(packageManager: PackageManager) : ApplicationsDataStore {
        return ApplicationsDataStoreImpl(packageManager)
    }

    @Singleton
    @Provides
    fun provideRatingDataStore(service : LookupService) : RatingDataStore {
        return RatingDataStoreImpl(service, NetworkModule.ACCESS_TOKEN)
    }

    @Singleton
    @Provides
    fun provideAppDataRepository(applicationsDataStore: ApplicationsDataStore, mapper: ApplicationMapper) : AppDataRepository {
        return AppDataRepositoryImpl(applicationsDataStore, mapper)
    }

    @Singleton
    @Provides
    fun provideRatingDataRepository(retingDataStore: RatingDataStore, mapper: RatingMapper) : RatingDataRepository {
        return RatingDataRepositoryImpl(retingDataStore, mapper)
    }
}