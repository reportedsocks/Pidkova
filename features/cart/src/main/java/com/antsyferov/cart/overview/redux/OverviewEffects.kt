package com.antsyferov.cart.overview.redux

import com.antsyferov.ui.redux.Reducer

sealed class OverviewEffects: Reducer.ViewEffect {
    data class NavigateToProductDetails(val productId: Long): OverviewEffects()
    data class ShowError(val text: String): OverviewEffects()
}