package com.antsyferov.cart.overview

import androidx.lifecycle.viewModelScope
import com.antsyferov.cart.overview.redux.OverviewEffects
import com.antsyferov.cart.overview.redux.OverviewEvents
import com.antsyferov.cart.overview.redux.OverviewReducer
import com.antsyferov.cart.overview.redux.OverviewState
import com.antsyferov.domain.use_cases.AddProductToCartUseCase
import com.antsyferov.domain.use_cases.LoadCartItemsUseCase
import com.antsyferov.domain.use_cases.RemoveProductFromCartUseCase
import com.antsyferov.ui.redux.BaseViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class OverviewViewModel(
    private val addProductToCartUseCase: AddProductToCartUseCase,
    private val removeProductFromCartUseCase: RemoveProductFromCartUseCase,
    private val getCartItemsUseCase: LoadCartItemsUseCase
): BaseViewModel<OverviewState, OverviewEvents, OverviewEffects>(
    initialState = OverviewState.initial(),
    reducer = OverviewReducer(addProductToCartUseCase, removeProductFromCartUseCase)
) {
    init {
        getCartItemsUseCase()
            .onEach {
                sendEvent(OverviewEvents.CartItemsLoaded(it))
            }
            .launchIn(viewModelScope)
    }

    override fun consumeEffect(effect: OverviewEffects): OverviewEffects? {
        return effect
    }
}