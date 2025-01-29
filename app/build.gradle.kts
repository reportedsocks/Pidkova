import com.android.build.api.dsl.Packaging

plugins {
    alias(libs.plugins.pidkova.android.application)
    alias(libs.plugins.pidkova.android.application.compose)
    alias(libs.plugins.pidkova.android.application.flavors)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose)
}

android {
    namespace = "com.antsyferov.pidkova"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.antsyferov.pidkova"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "META-INF/INDEX.LIST"
        }
    }
}

ksp {
    arg("KOIN_CONFIG_CHECK","true")
}

dependencies {
    implementation(project(":core:database"))
    implementation(project(":core:network"))
    implementation(project(":core:ui"))
    implementation(project(":domain"))

    implementation(project(":features:main"))
    implementation(project(":features:home"))
    implementation(project(":features:auth"))
    implementation(project(":features:onboarding"))
    implementation(project(":features:products"))
    implementation(project(":features:cart"))
    implementation(project(":features:profile"))
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.testManifest)


    implementation(libs.ktor.client.core)
}