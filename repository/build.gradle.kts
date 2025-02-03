plugins {
    alias(libs.plugins.pidkova.jvm.library)
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":core:domain"))
}