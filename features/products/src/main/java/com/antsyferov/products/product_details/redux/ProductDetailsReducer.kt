package com.antsyferov.products.product_details.redux

import com.antsyferov.domain.Result
import com.antsyferov.ui.mappers.toSnackbarText
import com.antsyferov.ui.redux.Reducer

class ProductDetailsReducer: Reducer<ProductDetailsState, ProductDetailsEvents, ProductDetailsEffects> {
    override fun reduce(
        previousState: ProductDetailsState,
        event: ProductDetailsEvents,
    ): Pair<ProductDetailsState, ProductDetailsEffects?> {
        return when(event) {
            is ProductDetailsEvents.AddCartClicked -> {
                previousState to ProductDetailsEffects.AddProductToCart
            }

            is ProductDetailsEvents.GoToCartCLicked -> {
                previousState to ProductDetailsEffects.GoToCart
            }

            is ProductDetailsEvents.NavigationUpClicked -> {
                previousState to ProductDetailsEffects.NavigateUp
            }

            is ProductDetailsEvents.ProductDetailsLoaded -> {
                when(event.result) {
                    is Result.Success -> { previousState.copy(product = event.result.data, isLoading = false) to null }
                    is Result.Error -> { previousState to ProductDetailsEffects.ShowSnackbar(event.result.error.toSnackbarText()) }
                }
            }
        }
    }
}