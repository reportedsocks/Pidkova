package com.antsyferov.pidkova.home

import androidx.compose.runtime.Immutable
import com.antsyferov.network.models.Product
import com.antsyferov.ui.redux.Reducer

@Immutable
sealed class HomeEvents : Reducer.ViewEvent {
    data object LoadProducts: HomeEvents()
    data class ProductsLoaded(val products: List<com.antsyferov.network.models.Product>): HomeEvents()
}
