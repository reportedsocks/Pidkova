package com.antsyferov.products.products_list.redux

import com.antsyferov.ui.redux.Reducer

class ProductsReducer: Reducer<ProductsState, ProductEvents, ProductEffects> {
    override fun reduce(
        previousState: ProductsState,
        event: ProductEvents,
    ): Pair<ProductsState, ProductEffects?> {
        return when(event) {
            is ProductEvents.ProductClicked -> {
                previousState to ProductEffects.NavigateToProductDetails(event.id)
            }
            is ProductEvents.ProductsLoaded -> {
                //TODO handle result
                previousState.copy(isLoading = false, products = event.products) to ProductEffects.ShowError
            }
        }
    }
}