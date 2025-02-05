package com.antsyferov.products.product_details.redux

import androidx.compose.runtime.Immutable
import com.antsyferov.products.models.ProductUi
import com.antsyferov.ui.redux.Reducer

@Immutable
data class ProductDetailsState(
    val product: ProductUi?,
    val isLoading: Boolean
): Reducer.ViewState {
    companion object {
        fun initial(): ProductDetailsState {
            return ProductDetailsState(
                product = null,
                isLoading = true
            )
        }
    }
}
