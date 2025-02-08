package com.antsyferov.profile.profile_data.redux

import com.antsyferov.domain.models.Profile
import com.antsyferov.profile.models.ProfileUi
import com.antsyferov.ui.redux.Reducer

data class ProfileState(
    val profile: ProfileUi?,
    val isLoading: Boolean,
    val isAuthorized: Boolean
): Reducer.ViewState {
    companion object {
        fun initial(): ProfileState {
            return ProfileState(
                profile = null,
                isLoading = true,
                isAuthorized = true
            )
        }
    }
}
