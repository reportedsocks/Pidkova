package com.antsyferov.auth.sign_in.redux

import com.antsyferov.ui.redux.Reducer

sealed class SignInEvents: Reducer.ViewEvent {
    data class NewLoginValue(val login: String): SignInEvents()
    data class NewPasswordValue(val login: String): SignInEvents()
    data object SignInClicked: SignInEvents()
}