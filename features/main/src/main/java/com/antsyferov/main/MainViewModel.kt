package com.antsyferov.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antsyferov.domain.interfaces.Datastore
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MainViewModel(
    private val datastore: Datastore
): ViewModel() {
    val hasViewedOnboarding = datastore.getViewedOnboarding()

    fun onOnboardingFinished() {
        viewModelScope.launch {
            datastore.setViewedOnboarding(true)
        }
    }
}