package com.antsyferov.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.antsyferov.profile.models.ProfileUi
import com.antsyferov.profile.profile_data.ProfileViewModel
import com.antsyferov.profile.profile_data.composables.NavigateToAuthPrompt
import com.antsyferov.profile.profile_data.composables.ProfileView
import com.antsyferov.profile.profile_data.redux.ProfileEffects
import com.antsyferov.profile.profile_data.redux.ProfileEvents
import com.antsyferov.profile.profile_data.redux.ProfileState
import com.antsyferov.ui.components.Button
import com.antsyferov.ui.components.ScreenContainer
import com.antsyferov.ui.components.SnackbarHost
import com.antsyferov.ui.rememberFlowWithLifecycle
import com.antsyferov.ui.theme.PidkovaTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreenRoot(
    onGoToAuth: () -> Unit
) {
    val viewModel = koinViewModel<ProfileViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val effects = rememberFlowWithLifecycle(viewModel.effect)

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val snackbarState = remember { SnackbarHostState() }

    LaunchedEffect(effects) {
        effects.collect {
            when(it) {
                is ProfileEffects.ShowError -> {
                    coroutineScope.launch {
                        snackbarState.showSnackbar(
                            message = it.text,
                            duration = SnackbarDuration.Short,
                            withDismissAction = true
                        )
                    }
                }
                is ProfileEffects.GoToAuth -> {
                    onGoToAuth()
                }
            }
        }
    }

    ProfileScreen(
        state = state,
        onEvent = viewModel::sendEvent,
        snackbarHostState = snackbarState
    )

}

@Composable
fun ProfileScreen(
    state: ProfileState,
    onEvent: (ProfileEvents) -> Unit,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
) {
    ScreenContainer(
        snackBar = { SnackbarHost(snackbarHostState) }
    ) {
        if (state.isAuthorized) {

            if (state.isLoading) {
                CircularProgressIndicator()
            } else {
                state.profile?.let {
                    ProfileView(profile = it)
                }
            }
        } else {
            NavigateToAuthPrompt(
                onEvent = onEvent
            )
        }
    }
}

@Composable
@Preview
fun ProfileScreenPreviewProfile() {
    PidkovaTheme {
        ProfileScreen(
            state = ProfileState.initial().copy(
                isLoading =  false,
                isAuthorized = true,
                profile = ProfileUi(
                    id = 1,
                    name = "John Doe",
                    email = "hello@gmail.com",
                    phone = "+45 345 54 324",
                    membership = "Bronze"
                )
            ),
            onEvent = {}
        )
    }
}

@Composable
@Preview
fun ProfileScreenPreviewAuth() {
    PidkovaTheme {
        ProfileScreen(
            state = ProfileState.initial().copy(
                isLoading =  false,
                isAuthorized = false,
            ),
            onEvent = {}
        )
    }
}