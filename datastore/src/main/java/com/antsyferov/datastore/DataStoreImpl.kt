package com.antsyferov.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.antsyferov.domain.interfaces.Datastore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "pidkova")
val ACCESS_TOKEN = stringPreferencesKey("access_token")
val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
val VIEWED_ONBOARDING = booleanPreferencesKey("viewed_onboarding")

@Single
class DataStoreImpl(
    private val context: Context
): Datastore {
    override suspend fun setAccessToken(accessToken: String) {
        context.dataStore.edit { prefs ->
            prefs[ACCESS_TOKEN] = accessToken
        }
    }

    override fun getAccessToken(): Flow<String?> {
        return context.dataStore.data.map { it[ACCESS_TOKEN] }
    }

    override suspend fun setRefreshToken(refreshToken: String) {
        context.dataStore.edit { prefs ->
            prefs[REFRESH_TOKEN] = refreshToken
        }
    }

    override fun getRefreshToken(): Flow<String?> {
        return context.dataStore.data.map { it[REFRESH_TOKEN] }
    }

    override suspend fun setViewedOnboarding(hasViewed: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[VIEWED_ONBOARDING] = hasViewed
        }
    }

    override fun getViewedOnboarding(): Flow<Boolean> {
        return context.dataStore.data.map { it[VIEWED_ONBOARDING] ?: false }
    }
}