package com.antsyferov.network

import com.antsyferov.domain.PidkovaException
import com.antsyferov.domain.Result
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.network.sockets.SocketTimeoutException
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
) : Result<out T> {
    return when(response.status.value) {
        in 200..299 -> {
            try {
                Result.Success(response.body<T>())
            } catch (e: NoTransformationFoundException) {
                Result.Error(PidkovaException.SERIALIZATION)
            }
        }
        401,403 -> Result.Error(PidkovaException.UNAUTHORIZED)
        408 -> Result.Error(PidkovaException.REQUEST_TIMEOUT)
        409 -> Result.Error(PidkovaException.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(PidkovaException.SERVER)
        else -> Result.Error(PidkovaException.UNKNOWN)
    }
}

suspend inline fun <reified T> safeCall(
    call: () -> HttpResponse
): Result<out T> {
    val response = try {
        call()
    } catch (e: SocketTimeoutException) {
        return Result.Error(PidkovaException.REQUEST_TIMEOUT)
    } catch (e: UnresolvedAddressException) {
        return Result.Error(PidkovaException.NO_INTERNET)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return Result.Error(PidkovaException.UNKNOWN)
    }

    return responseToResult(response)
}