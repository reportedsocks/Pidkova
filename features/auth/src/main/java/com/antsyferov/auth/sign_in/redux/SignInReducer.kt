package com.antsyferov.auth.sign_in.redux

import com.antsyferov.domain.Result
import com.antsyferov.ui.mappers.toSnackbarText
import com.antsyferov.ui.redux.Reducer

class SignInReducer : Reducer<SignInState, SignInEvents, SignInEffects>() {
    override fun reduce(
        previousState: SignInState,
        event: SignInEvents,
    ): Pair<SignInState, SignInEffects?> {
        return when(event) {
            is SignInEvents.SignInClicked -> {
                previousState.copy(isLoading = true) to SignInEffects.SignIn
            }
            is SignInEvents.NewLoginValue -> {
                previousState.copy(
                    login = event.login,
                    isLoginCorrect = !event.login.contains(' ')
                ) to null
            }
            is SignInEvents.NewPasswordValue -> {
                previousState.copy(
                    password = event.password,
                    isPasswordCorrect = event.password.length > 5
                ) to null
            }
            is SignInEvents.SignInCompleted -> {
                when(event.result) {
                    is Result.Success -> {
                        previousState.copy(isLoading = false) to SignInEffects.LoginSuccessful
                    }
                    is Result.Error -> {
                        previousState.copy(isLoading = false) to SignInEffects.ShowError(event.result.error.toSnackbarText())
                    }
                }
            }
        }
    }
}