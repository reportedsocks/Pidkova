package com.antsyferov.network.models.mappers

import com.antsyferov.domain.models.Product
import com.antsyferov.network.models.ProductDto

fun ProductDto.toDomain(): Product {
    return Product(
        id = id ?: 0,
        name = name ?: "",
        brand = brand ?: "",
        price = price ?: 0f,
        rating = rating ?: 0f,
        description = description ?: ""
    )
}