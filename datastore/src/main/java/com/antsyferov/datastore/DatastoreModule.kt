package com.antsyferov.datastore

import com.antsyferov.domain.interfaces.Datastore
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val datastoreModule = module {
    singleOf(::DataStoreImpl) { bind<Datastore>() }
}