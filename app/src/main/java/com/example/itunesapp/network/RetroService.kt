package com.example.itunesapp.network


import com.example.itunesapp.ListItunesData
import com.example.itunesapp.RecyclerList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    @GET("search")  // ?term=U2&media=music
    fun getItunesDataFromAPI(
        @Query("term") term : String,
        @Query("media") media : String
    ) : Call<ListItunesData>
}