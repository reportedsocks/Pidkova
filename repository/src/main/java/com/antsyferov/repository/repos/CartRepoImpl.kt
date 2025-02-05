package com.antsyferov.repository.repos

import com.antsyferov.domain.PidkovaException
import com.antsyferov.domain.Result
import com.antsyferov.domain.interfaces.CartRepo
import com.antsyferov.domain.models.CartItem
import com.antsyferov.repository.interfaces.LocalCartDataSource
import com.antsyferov.repository.interfaces.LocalProductsDataSource
import kotlinx.coroutines.flow.Flow

class CartRepoImpl(
    private val localCartDataSource: LocalCartDataSource,
    private val localProductsDataSource: LocalProductsDataSource
): CartRepo {

    override suspend fun addProductToCart(productId: Long): Result<Unit> {
        val cartItemResult = (localCartDataSource.getCartItem(productId) as? Result.Success) ?: return Result.Error(PidkovaException.UNKNOWN)

        cartItemResult.data?.let { cartItem ->
            return localCartDataSource.updateCartItem(cartItem.copy(quantity = cartItem.quantity + 1))
        }

        return localCartDataSource.insertCartItem(
            CartItem(
                product = (localProductsDataSource.getProduct(productId) as? Result.Success)?.data ?: return Result.Error(PidkovaException.UNKNOWN),
                quantity = 1
            )
        )
    }

    override suspend fun removeProductFromCart(productId: Long): Result<Unit> {
        val cartItemResult = (localCartDataSource.getCartItem(productId) as? Result.Success) ?: return Result.Error(PidkovaException.UNKNOWN)

        cartItemResult.data?.let { cartItem ->
            return if (cartItem.quantity > 1) {
                localCartDataSource.updateCartItem(cartItem.copy(quantity = cartItem.quantity - 1))
            } else {
                localCartDataSource.deleteCartItem(
                    CartItem(
                        product = (localProductsDataSource.getProduct(productId) as? Result.Success)?.data ?: return Result.Error(PidkovaException.UNKNOWN),
                        quantity = 1
                    )
                )
            }
        }

        return Result.Success(Unit)
    }

    override fun getCartItems(): Flow<Result<List<CartItem>>> {
        return localCartDataSource.getCartItems()
    }
}