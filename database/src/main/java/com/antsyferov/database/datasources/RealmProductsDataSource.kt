package com.antsyferov.database.datasources

import com.antsyferov.database.models.ProductEntity
import com.antsyferov.database.models.mappers.toDomain
import com.antsyferov.database.models.mappers.toEntity
import com.antsyferov.database.util.safeAccess
import com.antsyferov.domain.PidkovaException
import com.antsyferov.domain.Result
import com.antsyferov.domain.models.Product
import com.antsyferov.repository.interfaces.LocalProductsDataSource
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RealmProductsDataSource(
    private val realm: Realm
): LocalProductsDataSource {
    override suspend fun updateProducts(products: List<Product>): Result<Unit> {
        return safeAccess {
            realm.write {
                products
                    .map { it.toEntity() }
                    .forEach {
                        copyToRealm(it, UpdatePolicy.ALL)
                    }
            }
        }
    }

    override fun getProducts(): Flow<List<Product>> {
        return realm
            .query<ProductEntity>()
            .asFlow()
            .map { results ->
                results.list.toList()
                    .map { it.toDomain() }
            }
    }

    override suspend fun getProduct(id: Long): Result<Product> {
        val productEntity = realm
            .query<ProductEntity>("id == $0", id)
            .first()
            .find()
        return if (productEntity != null) {
            Result.Success(productEntity.toDomain())
        } else {
            Result.Error(PidkovaException.UNKNOWN)
        }
    }
}