package com.antsyferov.repository

import com.antsyferov.domain.PidkovaException
import com.antsyferov.domain.Result
import com.antsyferov.domain.models.Product
import com.antsyferov.repository.interfaces.LocalProductsDataSource
import com.antsyferov.repository.interfaces.RemoteProductsDataSource
import com.antsyferov.repository.repos.ProductsRepoImpl
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test


class ProductRepoTest {

    @MockK
    lateinit var remote: RemoteProductsDataSource

    @MockK
    lateinit var local: LocalProductsDataSource

    @BeforeTest
    fun setup() = MockKAnnotations.init(this)

    @Test
    fun repoUpdatesLocalSourceWhenRemoteLoadsData() = runTest {
        //given
        val productsRepo = ProductsRepoImpl(remote, local)
        coEvery { local.getProducts() } returns flowOf(productsList1)
        coEvery { remote.getProducts() } returns Result.Success(productsList2)
        coEvery { local.updateProducts(productsList2) } returns Result.Success(Unit)

        //when
        val results = productsRepo.getProducts().toList()

        //than
        assertThat(results).hasSize(1)
        assertThat(results[0]).isInstanceOf(Result.Success::class.java)
        assertThat((results[0] as Result.Success).data).isEqualTo(productsList1)
        coVerify {
            local.updateProducts(productsList2)
        }
    }


    @Test
    fun repoAddsErrorToTheOutputWhenRemoteFails() = runTest {
        //given
        val productsRepo = ProductsRepoImpl(remote, local)
        coEvery { local.getProducts() } returns flowOf(productsList1)
        coEvery { remote.getProducts() } returns Result.Error(PidkovaException.UNKNOWN)

        //when
        val results = productsRepo.getProducts().toList()

        //than
        assertThat(results).hasSize(2)

        //first result is from local
        assertThat(results[0]).isInstanceOf(Result.Success::class.java)
        assertThat((results[0] as Result.Success).data).isEqualTo(productsList1)

        // second result is remote error
        assertThat(results[1]).isInstanceOf(Result.Error::class.java)
        assertThat((results[1] as Result.Error).error).isEqualTo(PidkovaException.UNKNOWN)
    }


    private val productsList1 = List(10) { i ->
        Product(
            id = i.toLong(),
            name = "product $i",
            brand = "",
            price = 0f,
            rating = 0f,
            description = ""
        )
    }

    private val productsList2 = List(10) { i ->
        Product(
            id = i * 2L,
            name = "product ${i * 2}",
            brand = "",
            price = 0f,
            rating = 0f,
            description = ""
        )
    }

}