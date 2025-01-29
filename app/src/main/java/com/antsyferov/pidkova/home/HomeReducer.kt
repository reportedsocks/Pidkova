package com.antsyferov.pidkova.home

import com.antsyferov.ui.redux.Reducer

class HomeReducer: Reducer<HomeState, HomeEvents, HomeEffects> {
    override fun reduce(
        previousState: HomeState,
        event: HomeEvents,
    ): Pair<HomeState, HomeEffects?> {
        return when(event) {
            is HomeEvents.LoadProducts -> {
                previousState.copy(isLoadingProducts = true) to HomeEffects.LoadProducts
            }

            is HomeEvents.ProductsLoaded -> {
                previousState.copy(products = event.products) to HomeEffects.ShowToast
            }
        }
    }
}