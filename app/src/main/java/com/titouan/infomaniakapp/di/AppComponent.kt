package com.titouan.infomaniakapp.di

import com.titouan.infomaniakapp.BuildConfig
import com.titouan.infomaniakapp.data.remote.createRemoteModule
import com.titouan.infomaniakapp.features.di.featuresModule

val appComponent = listOf(
    repositoryModule,
    createRemoteModule(BuildConfig.API_BASE_URL),
    featuresModule
)