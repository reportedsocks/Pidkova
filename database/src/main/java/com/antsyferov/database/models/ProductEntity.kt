package com.antsyferov.database.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class ProductEntity: RealmObject {
    @PrimaryKey var id: Long = 0
    var name: String = ""
    var brand: String = ""
    var price: Float = 0f
    var rating: Float = 0f
    var description: String = ""
}