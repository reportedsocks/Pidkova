package com.antsyferov.database.models.mappers

import com.antsyferov.database.models.ProductEntity
import com.antsyferov.domain.models.Product

fun Product.toEntity(): ProductEntity {
    return ProductEntity().apply {
        id = this@toEntity.id
        name = this@toEntity.name
        brand = this@toEntity.brand
        price = this@toEntity.price
        rating = this@toEntity.rating
        description = this@toEntity.description
    }
}

fun ProductEntity.toDomain(): Product {
    return Product(
        id = id,
        name = name,
        brand = brand,
        price = price,
        rating = rating,
        description = description
    )
}