package com.antsyferov.repository.repos

import com.antsyferov.domain.Result
import com.antsyferov.domain.interfaces.ProductsRepo
import com.antsyferov.domain.models.Product
import com.antsyferov.repository.interfaces.RemoteProductsDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class ProductsRepoImpl(
    private val remoteProductsDataSource: RemoteProductsDataSource
): ProductsRepo {

    override suspend fun getProducts(): Flow<Result<List<Product>>> {
        return flow {
            emit(remoteProductsDataSource.getProducts())
        }
    }

}