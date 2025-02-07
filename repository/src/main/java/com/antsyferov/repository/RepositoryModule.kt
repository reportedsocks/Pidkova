package com.antsyferov.repository

import com.antsyferov.domain.interfaces.AuthRepo
import com.antsyferov.domain.interfaces.CartRepo
import com.antsyferov.domain.interfaces.ProductsRepo
import com.antsyferov.repository.repos.AuthRepoImpl
import com.antsyferov.repository.repos.CartRepoImpl
import com.antsyferov.repository.repos.ProductsRepoImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val repoModule = module {
    singleOf(::ProductsRepoImpl) { bind<ProductsRepo>() }
    singleOf(::CartRepoImpl) { bind<CartRepo>() }
    singleOf(::AuthRepoImpl) { bind<AuthRepo>() }
}