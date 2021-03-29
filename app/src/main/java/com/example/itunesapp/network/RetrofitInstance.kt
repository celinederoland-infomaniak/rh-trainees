package com.example.itunesapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitInstance  {

    companion object{
        val baseUrl = "https://itunes.apple.com/"

        fun getRetroInstance() : Retrofit{
            return  Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }
}