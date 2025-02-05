package com.antsyferov.database.datasources

import com.antsyferov.database.models.CartItemEntity
import com.antsyferov.database.models.mappers.toDomain
import com.antsyferov.database.models.mappers.toEntity
import com.antsyferov.database.util.safeAccess
import com.antsyferov.domain.Result
import com.antsyferov.domain.models.CartItem
import com.antsyferov.repository.interfaces.LocalCartDataSource
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.delete
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RealmCartDataSource(
    private val realm: Realm
): LocalCartDataSource {

    override suspend fun insertCartItem(cartItem: CartItem): Result<Unit> {
        return safeAccess {
            realm.write {
                val cartItemEntity = cartItem.toEntity()
                copyToRealm(cartItemEntity, UpdatePolicy.ALL)
            }
        }
    }

    override suspend fun getCartItem(productId: Long): Result<CartItem?> {
        val cartItemEntity = realm
            .query<CartItemEntity>(
                "product.id == $0", productId
            )
            .first()
            .find()

        return Result.Success(cartItemEntity?.toDomain())
    }

    override suspend fun updateCartItem(cartItem: CartItem): Result<Unit> {
        return safeAccess {
            realm
                .query<CartItemEntity>(
                    "product.id == $0", cartItem.product.id
                )
                .first()
                .find()
                ?.also { cartItemEntity ->
                    realm.write {
                        findLatest(cartItemEntity)?.quantity = cartItem.quantity
                    }
                }
        }
    }

    override suspend fun deleteCartItem(cartItem: CartItem): Result<Unit> {
        return safeAccess {
            realm
                .query<CartItemEntity>(
                    "product.id == $0", cartItem.product.id
                )
                .first()
                .find()
                ?.also {
                    realm.write {
                        findLatest(it)?.let { latest ->  delete(latest) }
                    }
                }
        }
    }

    override fun getCartItems(): Flow<Result<List<CartItem>>> {
        return realm
            .query<CartItemEntity>()
            .find()
            .asFlow()
            .map { Result.Success(it.list.map { item -> item.toDomain() }) }
    }
}