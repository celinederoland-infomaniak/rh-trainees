package com.titouan.infomaniakapp.features.search

import androidx.lifecycle.*
import com.titouan.infomaniakapp.data.models.Music
import com.titouan.infomaniakapp.data.repository.ItunesRepository
import com.titouan.infomaniakapp.features.utils.Resource
import java.lang.Exception

class ItunesMusicsViewModel(
    private val itunesRepository: ItunesRepository
): ViewModel() {

    private val searchTrigger = MutableLiveData<String>()

    val results: LiveData<Resource<List<Music>>> =
        searchTrigger.switchMap { term ->
            liveData {
                emit(Resource.Loading())
                try {
                    emit(Resource.Success(itunesRepository.searchMusics(term)))
                } catch (e: Exception) {
                    emit(Resource.Error<List<Music>>(e))
                }
            }
        }

    fun searchMusics(term: String) {
        searchTrigger.value = term
    }
}