package com.antsyferov.domain.models

data class Product(
    val id: Long,
    val name: String,
    val brand: String,
    val price: Float,
    val rating: Float,
)
