package com.antsyferov.domain.interfaces

import kotlinx.coroutines.flow.Flow

interface Datastore {

    suspend fun setAccessToken(accessToken: String)
    fun getAccessToken(): Flow<String?>

    suspend fun setRefreshToken(refreshToken: String)
    fun getRefreshToken(): Flow<String?>

    suspend fun setViewedOnboarding(hasViewed: Boolean)
    fun getViewedOnboarding(): Flow<Boolean>

}