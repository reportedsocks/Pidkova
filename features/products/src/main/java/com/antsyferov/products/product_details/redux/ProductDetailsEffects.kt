package com.antsyferov.products.product_details.redux

import androidx.compose.runtime.Immutable
import com.antsyferov.ui.redux.Reducer

@Immutable
sealed class ProductDetailsEffects: Reducer.ViewEffect {
    data class ShowSnackbar(val text: String): ProductDetailsEffects()
    data object GoToCart: ProductDetailsEffects()
    data object AddProductToCart: ProductDetailsEffects()
    data object NavigateUp: ProductDetailsEffects()
}