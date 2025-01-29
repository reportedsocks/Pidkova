package com.antsyferov.products.products_list

import androidx.lifecycle.viewModelScope
import com.antsyferov.domain.use_cases.LoadProductsUseCase
import com.antsyferov.products.models.mappers.toUi
import com.antsyferov.products.products_list.redux.ProductEffects
import com.antsyferov.products.products_list.redux.ProductEvents
import com.antsyferov.products.products_list.redux.ProductsReducer
import com.antsyferov.products.products_list.redux.ProductsState
import com.antsyferov.ui.redux.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class ProductsViewModel(
    private val loadProductsUseCase: LoadProductsUseCase
): BaseViewModel<ProductsState, ProductEvents, ProductEffects>(
    ProductsState.initial(),
    ProductsReducer()
) {

    init {
        loadProducts()
    }

    override fun consumeEffect(effect: ProductEffects): ProductEffects? {
        return effect
    }

    private fun loadProducts() {
        viewModelScope.launch {
            delay(2000)
            val products = loadProductsUseCase()
            sendEvent(ProductEvents.ProductsLoaded(products = products.map { it.toUi() }))
        }
    }
}