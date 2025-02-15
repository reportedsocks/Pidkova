package com.antsyferov.profile.profile_data.redux

import com.antsyferov.domain.Result
import com.antsyferov.profile.models.mappers.toUi
import com.antsyferov.ui.mappers.toSnackbarText
import com.antsyferov.ui.redux.Reducer

class ProfileReducer: Reducer<ProfileState, ProfileEvents, ProfileEffects>() {
    override fun reduce(
        previousState: ProfileState,
        event: ProfileEvents,
    ): Pair<ProfileState, ProfileEffects?> {
        return when(event) {
            is ProfileEvents.ProfileLoaded -> {
                when(event.result) {
                    is Result.Success -> {
                        previousState.copy(profile = event.result.data.toUi(), isLoading = false) to null
                    }
                    is Result.Error -> {
                        previousState to ProfileEffects.ShowError(event.result.error.toSnackbarText())
                    }
                }
            }
            is ProfileEvents.AuthClicked -> {
                previousState to ProfileEffects.GoToAuth
            }
            is ProfileEvents.AuthChanged -> {
                previousState.copy(isAuthorized = event.isAuthorized) to null
            }
            is ProfileEvents.LogOutClicked -> {
                previousState to ProfileEffects.LogOut
            }
        }
    }
}