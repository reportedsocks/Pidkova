package com.antsyferov.database.util

import com.antsyferov.domain.PidkovaException
import com.antsyferov.domain.Result
import java.lang.IllegalArgumentException

suspend fun <T> safeAccess(transaction: suspend () -> T): Result<T> {
    return try {
        Result.Success(transaction())
    } catch (e: IllegalArgumentException) {
        Result.Error(PidkovaException.UNKNOWN)
    } catch (e: Exception) {
        Result.Error(PidkovaException.DISK_FULL)
    }
}