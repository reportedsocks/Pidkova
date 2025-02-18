package com.antsyferov.network

import com.antsyferov.domain.Result
import com.antsyferov.domain.interfaces.Datastore
import com.antsyferov.network.datasources.KtorProfileDataSource
import com.antsyferov.repository.interfaces.LocalCartDataSource
import com.antsyferov.repository.interfaces.LocalProductsDataSource
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class KtorClientTest {

    @MockK
    lateinit var datastore: Datastore

    // Where testing environment is running, since test executes on local JVM and not emulator
    // have to use 'localhost' over 'http://10.0.2.2'
    val constantsProvider = object : ConstantsProvider {
        override fun getBaseUrl() = "http://localhost:3000/"
    }

    @BeforeTest
    fun setup() = MockKAnnotations.init(this)

    @Test
    fun ktorAuthorizesRequestsAndUpdatesTokens() = runTest {
        //given
        val client = NetworkModule().provideAuthorisedClient(
            datastore,
            constantsProvider
        )
        val profileDataSource = KtorProfileDataSource(client)
        coEvery { datastore.getAccessToken() } returns flowOf("access")
        coEvery { datastore.getRefreshToken() } returns flowOf("refresh")
        coEvery { datastore.setRefreshToken(any()) } just Runs
        coEvery { datastore.setAccessToken(any()) } just Runs

        //when
        val result = profileDataSource.getProfile()
        advanceUntilIdle()

        //then
        assertThat(result).isInstanceOf(Result.Success::class.java)
        coVerify {
            // request has been authorized
            datastore.getAccessToken()
            datastore.getRefreshToken()

            // tokens were updated
            datastore.setRefreshToken(any())
            datastore.setAccessToken(any())
        }
    }

}