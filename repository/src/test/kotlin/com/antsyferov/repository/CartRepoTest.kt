package com.antsyferov.repository

import com.antsyferov.domain.Result
import com.antsyferov.domain.models.CartItem
import com.antsyferov.domain.models.Product
import com.antsyferov.repository.interfaces.LocalCartDataSource
import com.antsyferov.repository.interfaces.LocalProductsDataSource
import com.antsyferov.repository.repos.CartRepoImpl
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class CartRepoTest {

    @MockK
    lateinit var localProducts: LocalProductsDataSource

    @MockK
    lateinit var localCart: LocalCartDataSource

    @BeforeTest
    fun setup() = MockKAnnotations.init(this)

    @Test
    fun repoUpdatesItemIfItAlreadyExists() = runTest {
        //given
        val cartRepo = CartRepoImpl(localCart, localProducts)

        val cartItemSlot = slot<CartItem>()
        coEvery { localCart.getCartItem(cartItem.product.id) } returns Result.Success(cartItem)
        coEvery { localCart.updateCartItem(capture(cartItemSlot)) } returns Result.Success(Unit)

        //when
        cartRepo.addProductToCart(cartItem.product.id)

        //than
        assertThat(cartItemSlot.captured).isEqualTo(cartItem.copy(quantity = cartItem.quantity + 1))
        coVerify {
            localCart.getCartItem(cartItem.product.id)
            localCart.updateCartItem(any())
        }
    }


    @Test
    fun repoAddsNewItemIfItDoesntExist() = runTest {
        //given
        val cartRepo = CartRepoImpl(localCart, localProducts)
        val cartItemSlot = slot<CartItem>()

        coEvery { localCart.getCartItem(cartItem.product.id) } returns Result.Success(null)
        coEvery { localProducts.getProduct(cartItem.product.id) } returns Result.Success(product)
        coEvery { localCart.insertCartItem(capture(cartItemSlot)) } returns Result.Success(Unit)

        //when
        cartRepo.addProductToCart(cartItem.product.id)

        //than
        assertThat(cartItemSlot.captured).isEqualTo(cartItem)
        coVerify {
            localCart.getCartItem(cartItem.product.id)
            localCart.insertCartItem(any())
            localProducts.getProduct(cartItem.product.id)
        }
    }

    @Test
    fun repoDecrementsItemIfPossible() = runTest {
        //given
        val cartRepo = CartRepoImpl(localCart, localProducts)
        val cartItemSlot = slot<CartItem>()
        coEvery { localCart.getCartItem(cartItem.product.id) } returns Result.Success(cartItem.copy(quantity = 2))
        coEvery { localCart.updateCartItem(capture(cartItemSlot)) } returns Result.Success(Unit)

        //when
        cartRepo.removeProductFromCart(cartItem.product.id)

        //than
        assertThat(cartItemSlot.captured).isEqualTo(cartItem)
        coVerify {
            localCart.getCartItem(cartItem.product.id)
            localCart.updateCartItem(any())
        }
    }

    @Test
    fun repoDeleteCartItemIfQuantityIs1() = runTest {
        //given
        val cartRepo = CartRepoImpl(localCart, localProducts)
        val cartItemSlot = slot<CartItem>()
        coEvery { localCart.getCartItem(cartItem.product.id) } returns Result.Success(cartItem)
        coEvery { localCart.deleteCartItem(capture(cartItemSlot)) } returns Result.Success(Unit)

        //when
        cartRepo.removeProductFromCart(cartItem.product.id)

        //than
        assertThat(cartItemSlot.captured).isEqualTo(cartItem)
        coVerify {
            localCart.getCartItem(cartItem.product.id)
            localCart.deleteCartItem(any())
        }
    }

    val product = Product(
        id = 1,
        name = "product 1",
        brand = "",
        price = 0f,
        rating = 0f,
        description = ""
    )

    val cartItem = CartItem(
        product,
        1
    )
}