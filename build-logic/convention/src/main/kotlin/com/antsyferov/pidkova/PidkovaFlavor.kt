package com.antsyferov.pidkova

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor

@Suppress("EnumEntryName")
enum class FlavorDimension {
    contentType,
    vendor
}

@Suppress("EnumEntryName")
enum class PidkovaFlavour(val dimension: FlavorDimension, val applicationIdSuffix: String? = null, val versionNameSuffix: String? = null) {
    demo(FlavorDimension.contentType, applicationIdSuffix = ".demo"),
    prod(FlavorDimension.contentType),
    eldorado(FlavorDimension.vendor, applicationIdSuffix = ".eldorado", versionNameSuffix = "-eldorado"),
    novus(FlavorDimension.vendor, applicationIdSuffix = ".novus", versionNameSuffix = "-novus")
}

fun configureFlavors(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    flavorConfigurationBlock: ProductFlavor.(flavor: PidkovaFlavour) -> Unit = {}
) {
    commonExtension.apply {
        FlavorDimension.values().forEach {
            flavorDimensions += it.name
        }
        productFlavors {
            PidkovaFlavour.values().forEach {
                create(it.name) {
                    dimension = it.dimension.name
                    flavorConfigurationBlock(this, it)
                    if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                        if (it.applicationIdSuffix != null) {
                            applicationIdSuffix = it.applicationIdSuffix
                        }
                        if (it.versionNameSuffix != null) {
                            versionNameSuffix = it.versionNameSuffix
                        }
                    }
                }
            }
        }
    }
}
