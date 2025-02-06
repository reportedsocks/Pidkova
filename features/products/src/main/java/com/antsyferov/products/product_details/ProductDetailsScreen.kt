package com.antsyferov.products.product_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.antsyferov.products.models.ProductUi
import com.antsyferov.products.product_details.composables.ProductDescription
import com.antsyferov.products.product_details.redux.ProductDetailsEffects
import com.antsyferov.products.product_details.redux.ProductDetailsEvents
import com.antsyferov.products.product_details.redux.ProductDetailsState
import com.antsyferov.ui.components.Button
import com.antsyferov.ui.components.ScreenContainer
import com.antsyferov.ui.components.SnackbarHost
import com.antsyferov.ui.components.Text
import com.antsyferov.ui.rememberFlowWithLifecycle
import com.antsyferov.ui.theme.Green40
import com.antsyferov.ui.theme.PidkovaTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductDetailsRoot(
    productId: Long,
    onNavBack: () -> Unit,
    onNavToCart: () -> Unit
) {
    val viewModel = koinViewModel<ProductDetailsViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val effects = rememberFlowWithLifecycle(viewModel.effect)

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val snackbarState = remember { SnackbarHostState() }

    LaunchedEffect(effects) {
        effects.collect {
            when(it) {
                is ProductDetailsEffects.ShowSnackbar -> {
                    coroutineScope.launch {
                        snackbarState.showSnackbar(
                            message = it.text,
                            duration = SnackbarDuration.Short,
                            withDismissAction = true
                        )
                    }
                }
                is ProductDetailsEffects.GoToCart -> onNavToCart()
                is ProductDetailsEffects.NavigateUp -> onNavBack()
                else -> {}
            }
        }
    }

    LaunchedEffect(productId) {
        viewModel.loadProduct(id = productId)
    }

    ProductDetailsScreen(
        state = state,
        onEvent = viewModel::sendEvent,
        snackbarHostState = snackbarState
    )
}

@Composable
fun ProductDetailsScreen(
    state: ProductDetailsState,
    onEvent: (ProductDetailsEvents) -> Unit,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
) {
    ScreenContainer(
        snackBar = { SnackbarHost(snackbarHostState) }
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {

            state.product?.let {
                ProductDescription(
                    product = it
                )
            }

            IconButton(
                onClick = { onEvent(ProductDetailsEvents.NavigationUpClicked) },
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Go back",
                    tint = Color.Black
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PidkovaTheme.dimensions.paddingLarge)
                    .align(Alignment.BottomCenter)
            ) {
                Button(
                    text = "Add to Cart",
                    onClick = { onEvent(ProductDetailsEvents.AddCartClicked(state.product?.id ?: 0)) },
                    modifier = Modifier.weight(1f)
                )
                Button(
                    text = "Go to Cart",
                    onClick = { onEvent(ProductDetailsEvents.GoToCartCLicked) },
                    modifier = Modifier.weight(1f)
                )
            }


        }
    }
}

@Composable
@Preview
fun ProductDetailsPreview() {
    PidkovaTheme {
        ProductDetailsScreen(
            state = ProductDetailsState.initial().copy(
                product = ProductUi(
                    1,
                    "Phone",
                    "Samsung",
                    12.3f,
                    4.3f,
                    "Lorem ipsum bla bla bla"
                ),
                isLoading = false
            ),
            onEvent = {}
        )
    }
}