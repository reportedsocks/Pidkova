package com.antsyferov.database

import com.antsyferov.database.datasources.RealmProductsDataSource
import com.antsyferov.database.models.ProductEntity
import com.antsyferov.repository.interfaces.LocalProductsDataSource
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
class DatabaseModule {

    @Single
    fun provideRealm(): Realm {
        return Realm.open(
            configuration = RealmConfiguration.create(
                schema = setOf(
                    ProductEntity::class
                )
            )
        )
    }

    @Single
    fun provideRealProductsDataSource(
        realm: Realm
    ): LocalProductsDataSource {
        return RealmProductsDataSource(realm)
    }
}