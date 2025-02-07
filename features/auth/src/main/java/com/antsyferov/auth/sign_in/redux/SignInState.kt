package com.antsyferov.auth.sign_in.redux

import androidx.compose.runtime.Immutable
import com.antsyferov.ui.redux.Reducer

@Immutable
data class SignInState(
    val login: String,
    val password: String,
    val isLoginCorrect: Boolean,
    val isPasswordCorrect: Boolean,
    val isLoading: Boolean
): Reducer.ViewState {
    companion object {
        fun initial(): SignInState {
            return SignInState(
                login = "",
                password = "",
                isLoginCorrect = false,
                isPasswordCorrect = false,
                isLoading = false,
            )
        }
    }
}