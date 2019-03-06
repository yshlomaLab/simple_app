package com.intelity.test.di.modules

import com.intelity.data.api.LookupService
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Added interceptor to log request and response.
 * Basic access token for 50 requests. Should be replaced with new to retrieve data.
 */
@Module
class NetworkModule {

    companion object {
        const val URL = "https://data.42matters.com/"
        const val ACCESS_TOKEN = "b1da591539635827ccde2d32e0b85fcbc3e30572"
    }

    @Singleton
    @Provides
    fun provideRetrofit(): LookupService {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(URL)
            .client(httpClient.build())
            .build().create(LookupService::class.java)
    }
}