package com.antsyferov.products.products_list.redux

import androidx.compose.runtime.Immutable
import com.antsyferov.products.models.ProductUi
import com.antsyferov.ui.redux.Reducer

@Immutable
data class ProductsState(
    val products: List<ProductUi>,
    val isLoading: Boolean
): Reducer.ViewState {
    companion object {
        fun initial() = ProductsState(
            products = emptyList(),
            isLoading = true
        )
    }
}