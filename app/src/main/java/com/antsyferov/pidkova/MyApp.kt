package com.antsyferov.pidkova

import android.app.Application
import com.antsyferov.domain.DomainModule
import com.antsyferov.network.NetworkModule
import com.antsyferov.products.ProductsModule
import com.antsyferov.repository.repoModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.ksp.generated.module

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(
                repoModule,
                NetworkModule().module,
                DomainModule().module,
                ProductsModule().module
            )
        }
    }

}