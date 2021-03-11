package com.titouan.infomaniakapp.data.remote

import android.content.Context
import com.titouan.infomaniakapp.data.remote.di.ItunesApiService
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun createRemoteModule(baseUrl: String) = module {
    single { provideRetrofitItunesStoreApi(get(), baseUrl) }

    factory { provideOkHttpClient(androidContext()) }
    factory { get<Retrofit>().create(ItunesApiService::class.java) }
}

private fun provideRetrofitItunesStoreApi(okHttpClient: OkHttpClient, baseUrl: String): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

private fun provideOkHttpClient(context: Context): OkHttpClient =
    OkHttpClient().newBuilder().build()