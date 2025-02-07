package com.antsyferov.auth.sign_in.redux

import com.antsyferov.domain.use_cases.SignInUseCase
import com.antsyferov.ui.redux.Reducer

class SignInReducer(
    private val signInUseCase: SignInUseCase
): Reducer<SignInState, SignInEvents, SignInEffects>() {
    override fun reduce(
        previousState: SignInState,
        event: SignInEvents,
    ): Pair<SignInState, SignInEffects?> {
        return when(event) {
            is SignInEvents.SignInClicked -> {
                previousState to null
            }
            is SignInEvents.NewLoginValue -> {
                previousState to null
            }
            is SignInEvents.NewPasswordValue -> {
                previousState to null
            }
        }
    }
}