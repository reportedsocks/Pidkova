package com.antsyferov.products.products_list.redux

import androidx.compose.runtime.Immutable
import com.antsyferov.products.models.ProductUi
import com.antsyferov.ui.redux.Reducer

@Immutable
sealed class ProductEvents: Reducer.ViewEvent {
    data class ProductClicked(val id: Long): ProductEvents()
    data class ProductsLoaded(val products: List<ProductUi>): ProductEvents()
}