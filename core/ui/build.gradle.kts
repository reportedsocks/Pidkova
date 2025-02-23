plugins {
    alias(libs.plugins.pidkova.android.library)
    alias(libs.plugins.pidkova.android.library.compose)
    alias(libs.plugins.pidkova.string.generation)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.antsyferov.ui"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(kotlin("reflect"))
}

tasks.named<StringGenerationTask>("stringGeneration") {
    source = "$projectDir/strings.csv"
    prefix = "core_ui_"
}