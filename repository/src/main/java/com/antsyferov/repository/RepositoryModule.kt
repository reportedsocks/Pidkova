package com.antsyferov.repository

import com.antsyferov.domain.interfaces.ProductsRepo
import com.antsyferov.repository.repos.ProductsRepoImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val repoModule = module {
    singleOf(::ProductsRepoImpl) { bind<ProductsRepo>() }
}