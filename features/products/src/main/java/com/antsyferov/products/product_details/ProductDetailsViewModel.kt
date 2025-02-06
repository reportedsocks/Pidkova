package com.antsyferov.products.product_details

import androidx.lifecycle.viewModelScope
import com.antsyferov.domain.map
import com.antsyferov.domain.use_cases.AddProductToCartUseCase
import com.antsyferov.domain.use_cases.GetProductUseCase
import com.antsyferov.products.models.mappers.toUi
import com.antsyferov.products.product_details.redux.ProductDetailsEffects
import com.antsyferov.products.product_details.redux.ProductDetailsEvents
import com.antsyferov.products.product_details.redux.ProductDetailsReducer
import com.antsyferov.products.product_details.redux.ProductDetailsState
import com.antsyferov.ui.redux.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class ProductDetailsViewModel(
    private val getProductUseCase: GetProductUseCase,
    private val addProductToCartUseCase: AddProductToCartUseCase
): BaseViewModel<ProductDetailsState, ProductDetailsEvents, ProductDetailsEffects> (
    initialState = ProductDetailsState.initial(),
    reducer = ProductDetailsReducer(addProductToCartUseCase)
){

    override fun consumeEffect(effect: ProductDetailsEffects): ProductDetailsEffects? {
        return effect
    }

    fun loadProduct(id: Long) {
        viewModelScope.launch {
            val result = getProductUseCase(id)
            sendEvent(ProductDetailsEvents.ProductDetailsLoaded(result.map { it.toUi() }))
        }
    }
}