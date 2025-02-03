package com.antsyferov.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto (
    @SerialName("id")
    val id: Long? = null,
    @SerialName("name")
    val name: String? = "",
    @SerialName("brand")
    val brand: String? = "",
    @SerialName("price")
    val price: Float? = 0f,
    @SerialName("stock")
    val stock: Int? = 0,
    @SerialName("rating")
    val rating: Float? = 0f,
    @SerialName("description")
    val description: String? = "",
)