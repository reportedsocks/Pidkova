package com.antsyferov.pidkova

import android.app.Application
import com.antsyferov.auth.AuthModule
import com.antsyferov.cart.CartModule
import com.antsyferov.database.DatabaseModule
import com.antsyferov.datastore.datastoreModule
import com.antsyferov.domain.DomainModule
import com.antsyferov.main.MainModule
import com.antsyferov.network.NetworkModule
import com.antsyferov.products.ProductsModule
import com.antsyferov.profile.ProfileModule
import com.antsyferov.repository.repoModule
import com.antsyferov.ui.CoreUIModule
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
                datastoreModule,
                AppModule().module,
                NetworkModule().module,
                DatabaseModule().module,
                DomainModule().module,
                CoreUIModule().module,
                ProductsModule().module,
                CartModule().module,
                AuthModule().module,
                ProfileModule().module,
                MainModule().module
            )
        }
    }

}