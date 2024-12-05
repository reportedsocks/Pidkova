plugins {
    alias(libs.plugins.pidkova.android.library)
    alias(libs.plugins.pidkova.android.library.compose)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.antsyferov.designsystem"
}

dependencies {

}