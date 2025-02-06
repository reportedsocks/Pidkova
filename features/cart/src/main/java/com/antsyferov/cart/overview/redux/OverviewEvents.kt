package com.antsyferov.cart.overview.redux

import com.antsyferov.domain.Result
import com.antsyferov.domain.models.CartItem
import com.antsyferov.ui.redux.Reducer

sealed class OverviewEvents: Reducer.ViewEvent {

    data class CartItemsLoaded(val result: Result<List<CartItem>>): OverviewEvents()
    data class AddProductClicked(val productId: Long): OverviewEvents()
    data class RemoveProductClicked(val productId: Long): OverviewEvents()
    data class ViewProductClicked(val productId: Long): OverviewEvents()
}