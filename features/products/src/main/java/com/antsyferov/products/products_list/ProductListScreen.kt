package com.antsyferov.products.products_list

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.antsyferov.products.products_list.redux.ProductEffects
import com.antsyferov.products.products_list.redux.ProductEvents
import com.antsyferov.products.products_list.redux.ProductsState
import com.antsyferov.ui.components.Text
import com.antsyferov.ui.rememberFlowWithLifecycle
import com.antsyferov.ui.theme.PidkovaTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductListRoot(
    onNavToProductDetails: (Long) -> Unit
) {
    val viewModel = koinViewModel<ProductsViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val effects = rememberFlowWithLifecycle(viewModel.effect)

    val context = LocalContext.current

    LaunchedEffect(effects) {
        effects.collect {
            when(it) {
                is ProductEffects.ShowError -> {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
                is ProductEffects.NavigateToProductDetails -> {
                    onNavToProductDetails(it.id)
                }
            }
        }
    }

    ProductListScreen(
        state = state,
        onEvent = viewModel::sendEvent
    )
}

@Composable
fun ProductListScreen(
    state: ProductsState,
    onEvent: (ProductEvents) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PidkovaTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                state.products.forEach { product ->
                    Text(product.name)
                }
            }
        }
    }
}

@Composable
@Preview
fun ProductListScreenPreview() {
    PidkovaTheme {
        ProductListScreen(
            state = ProductsState.initial(),
            onEvent = {}
        )
    }
}