package com.antsyferov.pidkova

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    factoryOf(::MyMainClass)

    singleOf(::SomeImplementation) { bind<SomeInterface>() }
}

@Module
@ComponentScan
class AppModule