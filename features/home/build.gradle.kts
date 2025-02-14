plugins {
    alias(libs.plugins.pidkova.android.feature)
    alias(libs.plugins.pidkova.android.library.compose)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.antsyferov.home"
}

dependencies {
    implementation(project(":features:products"))
    implementation(project(":features:cart"))
    implementation(project(":features:profile"))
}