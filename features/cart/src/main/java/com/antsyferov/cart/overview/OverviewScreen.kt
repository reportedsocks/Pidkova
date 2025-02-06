package com.antsyferov.cart.overview

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
import com.antsyferov.cart.models.CartItemUi
import com.antsyferov.cart.overview.composables.CartItem
import com.antsyferov.cart.overview.composables.CartItemsList
import com.antsyferov.cart.overview.redux.OverviewEffects
import com.antsyferov.cart.overview.redux.OverviewEvents
import com.antsyferov.cart.overview.redux.OverviewState
import com.antsyferov.ui.components.Button
import com.antsyferov.ui.components.ScreenContainer
import com.antsyferov.ui.components.SnackbarHost
import com.antsyferov.ui.rememberFlowWithLifecycle
import com.antsyferov.ui.theme.PidkovaTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun OverviewScreenRoot(
    onNavToProductDetails: (Long) -> Unit
) {
    val viewModel = koinViewModel<OverviewViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val effects = rememberFlowWithLifecycle(viewModel.effect)

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val snackbarState = remember { SnackbarHostState() }

    LaunchedEffect(effects) {
        effects.collect {
            when(it) {
                is OverviewEffects.ShowError -> {
                    coroutineScope.launch {
                        snackbarState.showSnackbar(
                            message = it.text,
                            duration = SnackbarDuration.Short,
                            withDismissAction = true
                        )
                    }
                }
                is OverviewEffects.NavigateToProductDetails -> {
                    onNavToProductDetails(it.productId)
                }
            }
        }
    }

    OverviewScreen(
        state = state,
        onEvent = viewModel::sendEvent,
        snackbarHostState = snackbarState
    )
}

@Composable
fun OverviewScreen(
    state: OverviewState,
    onEvent: (OverviewEvents) -> Unit,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
) {
    ScreenContainer(
        snackBar = { SnackbarHost(snackbarHostState) }
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            CartItemsList(
                items = state.cartItems,
                onEvent = onEvent,
                modifier = Modifier.fillMaxSize()
            )

            Button(
                text = "Go to Checkout",
                onClick = {},
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(PidkovaTheme.dimensions.paddingLarge)
            )
        }
    }
}

@Composable
@Preview
fun OverviewScreenPreview() {
    PidkovaTheme {
        OverviewScreen(
            state = OverviewState.initial().copy(
                isLoading = false,
                cartItems = listOf(
                    CartItemUi(
                        productId = 1,
                        name = "Phone",
                        price = 15.3f,
                        quantity = 3
                    )
                )
            ),
            onEvent = {}
        )
    }
}