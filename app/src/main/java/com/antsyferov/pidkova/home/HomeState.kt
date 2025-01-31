package com.antsyferov.pidkova.home

import androidx.compose.runtime.Immutable
import com.antsyferov.network.models.Product
import com.antsyferov.ui.redux.Reducer

@Immutable
data class HomeState(
    val products: List<com.antsyferov.network.models.Product>,
    val isLoadingProducts: Boolean
): Reducer.ViewState {
    companion object {
        fun initial(): HomeState {
            return HomeState(emptyList(), false)
        }
    }
}
