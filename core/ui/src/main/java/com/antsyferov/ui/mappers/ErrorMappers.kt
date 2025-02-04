package com.antsyferov.ui.mappers

import com.antsyferov.domain.PidkovaException

fun PidkovaException.toSnackbarText() : String {
    return when(this) {
        PidkovaException.NO_INTERNET -> "No internet access!"
        PidkovaException.SERVER,
        PidkovaException.UNAUTHORIZED,
        PidkovaException.SERIALIZATION,
        PidkovaException.TOO_MANY_REQUESTS,
        PidkovaException.REQUEST_TIMEOUT -> "Network problem"
        PidkovaException.DISK_FULL -> "Not enough disk space"
        PidkovaException.UNKNOWN -> "Unknown error"
    }
}