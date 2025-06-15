package com.cemp.domain.model.base

sealed interface NetworkResponse<out T> {
    data class Success<out T>(val data: T) : NetworkResponse<T>
    data class Error(val err: ApiError) : NetworkResponse<Nothing>
}

fun <T, R> NetworkResponse<T>.map(transformFunc: (T) -> R): NetworkResponse<R> {
    return when (this) {
        is NetworkResponse.Error -> {
            this
        }

        is NetworkResponse.Success -> {
            NetworkResponse.Success(transformFunc(this.data))
        }
    }
}