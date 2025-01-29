package com.antsyferov.products.products_list.redux

import androidx.compose.runtime.Immutable
import com.antsyferov.ui.redux.Reducer

@Immutable
sealed class ProductEffects: Reducer.ViewEffect {
    data object ShowError: ProductEffects()
    data class NavigateToProductDetails(val id: Long): ProductEffects()
}