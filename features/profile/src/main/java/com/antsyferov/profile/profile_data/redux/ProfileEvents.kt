package com.antsyferov.profile.profile_data.redux

import com.antsyferov.domain.Result
import com.antsyferov.domain.models.Profile
import com.antsyferov.ui.redux.Reducer

sealed class ProfileEvents: Reducer.ViewEvent {
    data class ProfileLoaded(val result: Result<Profile>): ProfileEvents()
    data class AuthChanged(val isAuthorized: Boolean): ProfileEvents()
    data object AuthClicked: ProfileEvents()
}