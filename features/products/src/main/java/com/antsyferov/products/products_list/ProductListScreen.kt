package com.antsyferov.products.products_list

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.antsyferov.products.products_list.composables.ProductsGrid
import com.antsyferov.products.products_list.redux.ProductEffects
import com.antsyferov.products.products_list.redux.ProductEvents
import com.antsyferov.products.products_list.redux.ProductsState
import com.antsyferov.ui.components.ScreenContainer
import com.antsyferov.ui.components.SnackBar
import com.antsyferov.ui.components.SnackbarHost
import com.antsyferov.ui.rememberFlowWithLifecycle
import com.antsyferov.ui.theme.PidkovaTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductListRoot(
    onNavToProductDetails: (Long) -> Unit
) {
    val viewModel = koinViewModel<ProductsViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val effects = rememberFlowWithLifecycle(viewModel.effect)

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val snackbarState = remember { SnackbarHostState() }

    LaunchedEffect(effects) {
        effects.collect {
            when(it) {
                is ProductEffects.ShowError -> {
                    coroutineScope.launch {
                        snackbarState.showSnackbar(
                            message = it.text,
                            duration = SnackbarDuration.Short,
                            withDismissAction = true
                        )
                    }
                }
                is ProductEffects.NavigateToProductDetails -> {
                    onNavToProductDetails(it.id)
                }
            }
        }
    }

    ProductListScreen(
        state = state,
        onEvent = viewModel::sendEvent,
        snackbarHostState = snackbarState
    )
}

@Composable
fun ProductListScreen(
    state: ProductsState,
    onEvent: (ProductEvents) -> Unit,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
) {
    ScreenContainer(
        snackBar = { SnackbarHost(snackbarHostState) }
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            ProductsGrid(
                products = state.products,
                onItemClick = { onEvent.invoke(ProductEvents.ProductClicked(it.id)) }
            )
        }
    }
}

@Composable
@Preview
fun ProductListScreenPreview() {
    PidkovaTheme {
        ProductListScreen(
            state = ProductsState.initial(),
            onEvent = {},
        )
    }
}