package com.antsyferov.cart.models

data class CartItemUi(
    val productId: Long,
    val name: String,
    val price: Float,
    val quantity: Int
)
