package com.antsyferov.cart.overview.redux

import com.antsyferov.cart.models.mappers.toUi
import com.antsyferov.domain.Result
import com.antsyferov.domain.use_cases.AddProductToCartUseCase
import com.antsyferov.domain.use_cases.RemoveProductFromCartUseCase
import com.antsyferov.ui.mappers.toSnackbarText
import com.antsyferov.ui.redux.Reducer
import kotlinx.coroutines.launch

class OverviewReducer(
    private val addProductToCartUseCase: AddProductToCartUseCase,
    private val removeProductFromCartUseCase: RemoveProductFromCartUseCase
): Reducer<OverviewState, OverviewEvents, OverviewEffects>() {

    override fun reduce(
        previousState: OverviewState,
        event: OverviewEvents,
    ): Pair<OverviewState, OverviewEffects?> {
        return when(event) {
            is OverviewEvents.CartItemsLoaded -> {
                when(event.result) {
                    is Result.Success -> {
                        previousState.copy(
                            cartItems = event.result.data.map { it.toUi() },
                            isLoading = false
                        ) to null
                    }
                    is Result.Error -> {
                        previousState to OverviewEffects.ShowError(event.result.error.toSnackbarText())
                    }
                }
            }
            is OverviewEvents.AddProductClicked -> {
                scope.launch { addProductToCartUseCase(event.productId) }
                previousState to null
            }
            is OverviewEvents.RemoveProductClicked -> {
                scope.launch { removeProductFromCartUseCase(event.productId) }
                previousState to null
            }
            is OverviewEvents.ViewProductClicked -> {
                previousState to OverviewEffects.NavigateToProductDetails(event.productId)
            }
        }
    }
}