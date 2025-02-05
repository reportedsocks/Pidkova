package com.antsyferov.domain.use_cases

import com.antsyferov.domain.Result
import com.antsyferov.domain.interfaces.CartRepo
import com.antsyferov.domain.models.CartItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Single

@Single
class LoadCartItemsUseCase(
    private val cartRepo: CartRepo
) {
    operator fun invoke(): Flow<Result<List<CartItem>>> {
        return cartRepo
            .getCartItems()
            .flowOn(Dispatchers.IO)
    }
}