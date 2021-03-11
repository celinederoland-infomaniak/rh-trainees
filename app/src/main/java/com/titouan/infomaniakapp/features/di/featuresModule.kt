package com.titouan.infomaniakapp.features.di

import com.titouan.infomaniakapp.features.search.ItunesMusicsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featuresModule = module {
    viewModel { ItunesMusicsViewModel(get()) }
}