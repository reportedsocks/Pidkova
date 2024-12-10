package com.antsyferov.pidkova.home

import androidx.compose.runtime.Immutable
import com.antsyferov.network.models.Product
import com.antsyferov.pidkova.mvi.Reducer

@Immutable
data class HomeState(
    val products: List<Product>,
    val isLoadingProducts: Boolean
): Reducer.ViewState {
    companion object {
        fun initial(): HomeState {
            return HomeState(emptyList(), false)
        }
    }
}
