package com.titouan.infomaniakapp.features

import androidx.lifecycle.*
import com.titouan.infomaniakapp.data.models.Music
import com.titouan.infomaniakapp.data.repository.ItunesRepository

class ItunesMusicsViewModel(
    private val itunesRepository: ItunesRepository
): ViewModel() {

    private val searchTrigger = MutableLiveData<String>()

    val results: LiveData<List<Music>> =
        searchTrigger.switchMap { term ->
            liveData {
                emit(itunesRepository.searchMusics(term))
            }
        }

    fun searchMusics(term: String) {
        searchTrigger.value = term
    }
}