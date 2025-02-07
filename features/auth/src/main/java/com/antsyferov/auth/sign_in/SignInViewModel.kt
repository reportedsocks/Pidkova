package com.antsyferov.auth.sign_in

import androidx.lifecycle.viewModelScope
import com.antsyferov.auth.sign_in.redux.SignInEffects
import com.antsyferov.auth.sign_in.redux.SignInEvents
import com.antsyferov.auth.sign_in.redux.SignInReducer
import com.antsyferov.auth.sign_in.redux.SignInState
import com.antsyferov.domain.use_cases.SignInUseCase
import com.antsyferov.ui.redux.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SignInViewModel(
    private val signInUseCase: SignInUseCase
): BaseViewModel<SignInState, SignInEvents, SignInEffects>(
    initialState = SignInState.initial(),
    reducer = SignInReducer()
) {
    override fun consumeEffect(effect: SignInEffects): SignInEffects? {
        return when(effect) {
            is SignInEffects.SignIn -> {
                viewModelScope.launch {
                    val result = signInUseCase(state.value.login, state.value.password)
                    sendEvent(SignInEvents.SignInCompleted(result))
                }
                null
            }
            else -> effect
        }
    }
}