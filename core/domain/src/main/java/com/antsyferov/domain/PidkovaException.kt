package com.antsyferov.domain

enum class PidkovaException {
    REQUEST_TIMEOUT,
    TOO_MANY_REQUESTS,
    NO_INTERNET,
    SERVER,
    SERIALIZATION,
    UNAUTHORIZED,
    DISK_FULL,
    UNKNOWN
}