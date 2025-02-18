package com.antsyferov.database.migration

import io.realm.kotlin.dynamic.getValue
import io.realm.kotlin.migration.AutomaticSchemaMigration

val migration = AutomaticSchemaMigration { migrationContext ->
    if (migrationContext.oldRealm.schemaVersion() < 1) {
        migrationContext.enumerate("ProductEntity") { oldObject, newObject ->
            newObject?.run {
                set("promoText", oldObject.getValue<String>("description"))
                set("discount", 0f)
            }
        }
    }
}