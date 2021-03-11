package com.titouan.infomaniakapp.features

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.titouan.infomaniakapp.data.repository.ItunesRepository
import kotlinx.coroutines.launch

class ItunesMusicsViewModel(
    private val itunesRepository: ItunesRepository
): ViewModel() {

    fun searchMusics(term: String) {
        viewModelScope.launch {
            val musics = itunesRepository.searchMusics(term)
            Log.d("Musics", "Found ${musics.size}")
        }
    }
}