package com.antsyferov.database.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class CartItemEntity: RealmObject {
    @PrimaryKey var id: ObjectId = ObjectId()
    var product: ProductEntity? = null
    var quantity: Int = 0
}