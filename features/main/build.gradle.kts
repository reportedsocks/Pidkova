plugins {
    alias(libs.plugins.pidkova.android.feature)
    alias(libs.plugins.pidkova.android.library.compose)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.antsyferov.main"
}

dependencies {
    implementation(project(":features:home"))
    implementation(project(":features:auth"))
    implementation(project(":features:onboarding"))
}