package com.antsyferov.pidkova.home

import androidx.lifecycle.viewModelScope
import com.antsyferov.network.RemoteApiImpl
import com.antsyferov.pidkova.mvi.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel(
    private val remoteApiImpl: RemoteApiImpl
) : BaseViewModel<HomeState, HomeEvents, HomeEffects>(
    HomeState.initial(),
    HomeReducer()
) {

    override fun consumeEffect(effect: HomeEffects): HomeEffects? {
        return when(effect) {
            is HomeEffects.LoadProducts -> {
                loadProducts()
                null
            }
            else -> effect
        }
    }

    //TODO error handling
    private fun loadProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            val products = remoteApiImpl.getProducts()

            sendEvent(HomeEvents.ProductsLoaded(products))
        }
    }

}