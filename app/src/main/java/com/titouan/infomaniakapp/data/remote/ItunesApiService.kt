package com.titouan.infomaniakapp.data.remote.di

import com.titouan.infomaniakapp.data.models.MusicsSearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesApiService {

    @GET("search")
    suspend fun search(
        @Query("term") term: String,
        @Query("media") media: String
    ): MusicsSearchResult
}