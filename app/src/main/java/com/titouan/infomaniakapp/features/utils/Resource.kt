package com.titouan.infomaniakapp.features.utils

sealed class Resource<out T : Any> {

    data class Loading<out T : Any>(val data: T? = null) : Resource<T>()
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error<out T : Any>(val throwable: Throwable, val data: T? = null) : Resource<T>()

    override fun toString(): String {
        return when (this) {
            is Loading<*> -> "Loading[data=$data"
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$throwable]"
        }
    }
}

val Resource<*>?.isLoading: Boolean
    get() = this is Resource.Loading<*>

val Resource<*>?.isError: Boolean
    get() = this is Resource.Error<*>