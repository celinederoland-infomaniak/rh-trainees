package com.titouan.infomaniakapp.data.repository

import com.titouan.infomaniakapp.data.models.Music
import com.titouan.infomaniakapp.data.remote.di.ItunesApiService

interface ItunesRepository {
    suspend fun searchMusics(term: String): List<Music>
}

class ItunesRepositoryImpl(
    private val itunesApiService: ItunesApiService
) : ItunesRepository {

    override suspend fun searchMusics(term: String): List<Music> =
        itunesApiService.search(term, MUSIC)
            .results

    companion object {
        const val MUSIC = "music"
    }
}