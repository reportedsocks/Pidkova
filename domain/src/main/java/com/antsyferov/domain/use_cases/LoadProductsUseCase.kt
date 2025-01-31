package com.antsyferov.domain.use_cases

import com.antsyferov.domain.models.Product
import org.koin.core.annotation.Single

@Single
class LoadProductsUseCase() {

    operator fun invoke(): List<Product> {
        return buildList {
            for (i in 1..50) {
                add(Product(
                    id = i.toLong(),
                    name = "Product $i",
                    brand = "Samsung",
                    price = 10f,
                    rating = 3.4f
                ))
            }
        }
    }
}