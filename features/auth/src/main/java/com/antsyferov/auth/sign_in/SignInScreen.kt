package com.antsyferov.auth.sign_in

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.antsyferov.auth.sign_in.redux.SignInEffects
import com.antsyferov.auth.sign_in.redux.SignInEvents
import com.antsyferov.auth.sign_in.redux.SignInState
import com.antsyferov.ui.components.ScreenContainer
import com.antsyferov.ui.components.SnackbarHost
import com.antsyferov.ui.components.Text
import com.antsyferov.ui.rememberFlowWithLifecycle
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignInScreenRoot(
    onCompletedAuth: () -> Unit
) {
    val viewModel = koinViewModel<SignInViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val effects = rememberFlowWithLifecycle(viewModel.effect)

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val snackbarState = remember { SnackbarHostState() }

    LaunchedEffect(effects) {
        effects.collect {
            when(it) {
                is SignInEffects.ShowError -> {
                    coroutineScope.launch {
                        snackbarState.showSnackbar(
                            message = it.text,
                            duration = SnackbarDuration.Short,
                            withDismissAction = true
                        )
                    }
                }
                is SignInEffects.LoginSuccessful -> {
                    onCompletedAuth()
                }
            }
        }
    }

    SignInScreen(
        state = state,
        onEvent = viewModel::sendEvent,
        snackbarHostState = snackbarState
    )
}

@Composable
fun SignInScreen(
    state: SignInState,
    onEvent: (SignInEvents) -> Unit,
    snackbarHostState: SnackbarHostState
) {
    ScreenContainer(
        snackBar = { SnackbarHost(snackbarHostState) }
    ) {
        Text("Sign in")
    }
}