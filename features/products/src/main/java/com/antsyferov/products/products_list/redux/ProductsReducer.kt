package com.antsyferov.products.products_list.redux

import com.antsyferov.domain.Result
import com.antsyferov.products.models.mappers.toUi
import com.antsyferov.ui.mappers.toSnackbarText
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
                when (event.result) {
                    is Result.Success -> {
                        previousState.copy(
                            isLoading = false,
                            products = event.result.data.map { it.toUi() }
                        ) to null
                    }
                    is Result.Error -> {
                        previousState to ProductEffects.ShowError(event.result.error.toSnackbarText())
                    }
                }

            }
        }
    }
}