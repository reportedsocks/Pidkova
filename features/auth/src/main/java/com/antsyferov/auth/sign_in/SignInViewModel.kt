package com.antsyferov.auth.sign_in

import com.antsyferov.auth.sign_in.redux.SignInEffects
import com.antsyferov.auth.sign_in.redux.SignInEvents
import com.antsyferov.auth.sign_in.redux.SignInReducer
import com.antsyferov.auth.sign_in.redux.SignInState
import com.antsyferov.domain.use_cases.SignInUseCase
import com.antsyferov.ui.redux.BaseViewModel
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SignInViewModel(
    private val signInUseCase: SignInUseCase
): BaseViewModel<SignInState, SignInEvents, SignInEffects>(
    initialState = SignInState.initial(),
    reducer = SignInReducer(signInUseCase)
) {
    override fun consumeEffect(effect: SignInEffects): SignInEffects? {
        return effect
    }
}