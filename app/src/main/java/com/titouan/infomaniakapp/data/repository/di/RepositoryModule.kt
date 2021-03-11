package com.titouan.infomaniakapp.di

import com.titouan.infomaniakapp.data.repository.AppDispatchers
import com.titouan.infomaniakapp.data.repository.ItunesRepository
import com.titouan.infomaniakapp.data.repository.ItunesRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repositoryModule = module {
    factory { AppDispatchers(Dispatchers.Main, Dispatchers.IO) }

    factory<ItunesRepository> { ItunesRepositoryImpl(get()) }
}