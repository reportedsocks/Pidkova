package com.antsyferov.products.product_details.redux

import androidx.compose.runtime.Immutable
import com.antsyferov.domain.Result
import com.antsyferov.products.models.ProductUi
import com.antsyferov.ui.redux.Reducer

@Immutable
sealed class ProductDetailsEvents: Reducer.ViewEvent {
    data object AddCartClicked: ProductDetailsEvents()
    data object GoToCartCLicked: ProductDetailsEvents()
    data object NavigationUpClicked: ProductDetailsEvents()
    data class ProductDetailsLoaded(val result: Result<ProductUi>): ProductDetailsEvents()
}