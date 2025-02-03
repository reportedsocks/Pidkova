package com.antsyferov.domain


sealed interface Result<T> {
    data class Success<T>(val data: T): Result<T>
    data class Error<T>(val error: PidkovaException): Result<T>
}

fun <T,R> Result<T>.map(map:(T) -> R): Result<R> {
    return when(this) {
        is Result.Success -> Result.Success(map(data))
        is Result.Error -> Result.Error(this.error)
    }
}
