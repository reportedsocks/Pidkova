package com.antsyferov.database

import com.antsyferov.database.migration.migration
import com.antsyferov.database.models.CartItemEntity
import com.antsyferov.database.models.ProductEntity
import com.google.common.truth.Truth.assertThat
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.File


class RealmMigrationTest {

    @Test
    fun realMigration_0To1() = runTest {
        val oldRealm = Realm.open(
            configuration = RealmConfiguration.Builder(
                schema = setOf(
                    ProductEntity::class,
                    CartItemEntity::class
                )
            )
                .migration(migration)
                .schemaVersion(0)
                .build()
        )

        oldRealm.write {
            copyToRealm(ProductEntity().apply {
                name = "product"
                description = "description"
                discount = -1f
            })
        }
        oldRealm.close()

        val realm = Realm.open(
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

        val product = realm.query<ProductEntity>().first().asFlow().map { it.obj }.first()

        assertThat(product).isNotNull()
        assertThat(product?.discount).isEqualTo(0f)
        assertThat(product?.promoText).isEqualTo("description")

        realm.close()
    }

    //doesn't work in @AfterTest, needs to be launched in a separate test
    @Test
    fun delete() {
        File("default.realm.lock").delete()
        File("default.realm").delete()
        File("default.realm.management").delete()
    }
}