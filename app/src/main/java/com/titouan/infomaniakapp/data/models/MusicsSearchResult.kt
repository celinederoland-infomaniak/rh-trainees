package com.titouan.infomaniakapp.data.models

import com.google.gson.annotations.SerializedName

data class MusicsSearchResult(
    @SerializedName("results")
    val results: List<Music>
)
