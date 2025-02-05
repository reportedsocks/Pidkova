package com.antsyferov.products.models.mappers

import com.antsyferov.domain.models.Product
import com.antsyferov.products.models.ProductUi

fun Product.toUi(): ProductUi {
    return ProductUi(
        id = id,
        name = name,
        brand = brand,
        price = price,
        rating = rating,
        description = description
    )
}