package com.antsyferov.domain.interfaces

import com.antsyferov.domain.Result
import com.antsyferov.domain.models.CartItem
import kotlinx.coroutines.flow.Flow

interface CartRepo {

    suspend fun addProductToCart(productId: Long): Result<Unit>

    suspend fun removeProductFromCart(productId: Long): Result<Unit>

    fun getCartItems(): Flow<Result<List<CartItem>>>

}