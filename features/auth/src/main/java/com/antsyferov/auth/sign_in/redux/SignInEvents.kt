package com.antsyferov.auth.sign_in.redux

import com.antsyferov.domain.Result
import com.antsyferov.ui.redux.Reducer

sealed class SignInEvents: Reducer.ViewEvent {
    data class NewLoginValue(val login: String): SignInEvents()
    data class NewPasswordValue(val password: String): SignInEvents()
    data object SignInClicked: SignInEvents()
    data class SignInCompleted(val result: Result<Unit>): SignInEvents()
}