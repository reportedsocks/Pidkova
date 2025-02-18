package com.antsyferov.database

import com.antsyferov.database.datasources.RealmCartDataSource
import com.antsyferov.database.datasources.RealmProductsDataSource
import com.antsyferov.database.migration.migration
import com.antsyferov.database.models.CartItemEntity
import com.antsyferov.database.models.ProductEntity
import com.antsyferov.repository.interfaces.LocalCartDataSource
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
            configuration = RealmConfiguration.Builder(
                schema = setOf(
                    ProductEntity::class,
                    CartItemEntity::class
                )
            )
                .migration(migration)
                .schemaVersion(1)
                .build()
        )
    }

    @Single
    fun provideRealProductsDataSource(
        realm: Realm
    ): LocalProductsDataSource {
        return RealmProductsDataSource(realm)
    }

    @Single
    fun provideRealCartItemsDataSource(
        realm: Realm
    ): LocalCartDataSource {
        return RealmCartDataSource(realm)
    }
}