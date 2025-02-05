package com.antsyferov.domain.use_cases

import com.antsyferov.domain.Result
import com.antsyferov.domain.interfaces.CartRepo
import org.koin.core.annotation.Single

@Single
class RemoveProductFromCartUseCase(
    private val cartRepo: CartRepo
) {

    suspend operator fun invoke(productId: Long) : Result<Unit> {
        return cartRepo.removeProductFromCart(productId)
    }

}