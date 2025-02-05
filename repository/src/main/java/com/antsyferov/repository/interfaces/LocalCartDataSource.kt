package com.antsyferov.repository.interfaces

import com.antsyferov.domain.Result
import com.antsyferov.domain.models.CartItem
import kotlinx.coroutines.flow.Flow

interface LocalCartDataSource {

    suspend fun insertCartItem(cartItem: CartItem): Result<Unit>

    suspend fun getCartItem(productId: Long): Result<CartItem?>

    suspend fun updateCartItem(cartItem: CartItem): Result<Unit>

    suspend fun deleteCartItem(cartItem: CartItem): Result<Unit>

    fun getCartItems(): Flow<Result<List<CartItem>>>

}