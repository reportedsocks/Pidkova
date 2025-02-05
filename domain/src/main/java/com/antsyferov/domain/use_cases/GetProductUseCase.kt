package com.antsyferov.domain.use_cases

import com.antsyferov.domain.Result
import com.antsyferov.domain.interfaces.ProductsRepo
import com.antsyferov.domain.models.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Single

@Single
class GetProductUseCase(
    private val repo: ProductsRepo
) {
    suspend operator fun invoke(id: Long): Result<Product> {
        return withContext(Dispatchers.IO) {
            repo.getProduct(id)
        }
    }
}