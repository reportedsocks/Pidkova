package com.antsyferov.domain


sealed interface Result<T> {
    data class Success<T>(val data: T): Result<T>
    data class Error(val error: PidkovaException): Result<Nothing>
}

fun <T,R> Result<T>.map(map:(T) -> R): Result<out R> {
    return when(this) {
        is Result.Success -> Result.Success(map(data))
        is Result.Error -> Result.Error(error)
    }
}
