package com.antsyferov.profile.profile_data.redux

import com.antsyferov.ui.redux.Reducer

sealed class ProfileEffects: Reducer.ViewEffect {
    data class ShowError(val text: String): ProfileEffects()
    data object GoToAuth: ProfileEffects()
    data object LogOut: ProfileEffects()
}