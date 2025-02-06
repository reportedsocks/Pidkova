package com.antsyferov.cart.overview.redux

import androidx.compose.runtime.Immutable
import com.antsyferov.cart.models.CartItemUi
import com.antsyferov.ui.redux.Reducer

@Immutable
data class OverviewState(
    val cartItems: List<CartItemUi>,
    val isLoading: Boolean
): Reducer.ViewState {
    companion object {
        fun initial(): OverviewState {
            return OverviewState(
                cartItems = emptyList(),
                isLoading = true
            )
        }
    }
}