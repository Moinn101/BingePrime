package com.moin.bingeprime.di

import com.moin.bingeprime.data.network.WatchmodeApi
import com.moin.bingeprime.data.repository.MediaRepository
import com.moin.bingeprime.ui.viewmodel.MediaViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        Retrofit.Builder()
            .baseUrl("https://api.watchmode.com/v1/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(WatchmodeApi::class.java)
    }

    single { MediaRepository(get()) }
    viewModel { MediaViewModel(get()) }
}