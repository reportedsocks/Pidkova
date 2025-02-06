package com.antsyferov.cart.models.mappers

import com.antsyferov.cart.models.CartItemUi
import com.antsyferov.domain.models.CartItem

fun CartItem.toUi(): CartItemUi {
    return CartItemUi(
        productId = product.id,
        name = product.name,
        price = product.price,
        quantity = quantity
    )
}