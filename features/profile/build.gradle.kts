plugins {
    alias(libs.plugins.pidkova.android.feature)
    alias(libs.plugins.pidkova.android.library.compose)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.antsyferov.profile"
}

dependencies {
}