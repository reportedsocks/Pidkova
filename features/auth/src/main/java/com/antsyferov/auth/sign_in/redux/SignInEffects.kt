package com.antsyferov.auth.sign_in.redux

import com.antsyferov.ui.redux.Reducer

sealed class SignInEffects: Reducer.ViewEffect {
    data class ShowError(val text: String): SignInEffects()
    data object LoginSuccessful: SignInEffects()
    data object SignIn: SignInEffects()
}