package com.antsyferov.database.models.mappers

import com.antsyferov.database.models.CartItemEntity
import com.antsyferov.domain.models.CartItem

fun CartItem.toEntity(): CartItemEntity {
    return CartItemEntity().apply {
        quantity = this@toEntity.quantity
        product = this@toEntity.product.toEntity()
    }
}

fun CartItemEntity.toDomain(): CartItem {
    return CartItem(
        product = this@toDomain.product?.toDomain() ?: throw IllegalStateException("Cart item should have a product assigned"),
        quantity = this@toDomain.quantity
    )
}