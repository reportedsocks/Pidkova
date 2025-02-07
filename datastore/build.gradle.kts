plugins {
    alias(libs.plugins.pidkova.android.library)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.antsyferov.datastore"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":domain"))

    implementation("androidx.datastore:datastore-preferences:1.1.2")
}