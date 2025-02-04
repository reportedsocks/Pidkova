package com.antsyferov.repository.repos

import com.antsyferov.domain.Result
import com.antsyferov.domain.interfaces.ProductsRepo
import com.antsyferov.domain.models.Product
import com.antsyferov.repository.interfaces.LocalProductsDataSource
import com.antsyferov.repository.interfaces.RemoteProductsDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach


class ProductsRepoImpl(
    private val remoteProductsDataSource: RemoteProductsDataSource,
    private val localProductsDataSource: LocalProductsDataSource
): ProductsRepo {

    override suspend fun getProducts(): Flow<Result<List<Product>>> {
        val localFLow = localProductsDataSource
            .getProducts()
            .map { Result.Success(it) }

        val remoteFlow = flow {
            emit(remoteProductsDataSource.getProducts())
        }.onEach { remoteResult ->
            if (remoteResult is Result.Success) {
                localProductsDataSource.updateProducts(remoteResult.data)
            }
        }.filter { it is Result.Error }

        return merge(localFLow, remoteFlow)
    }

}