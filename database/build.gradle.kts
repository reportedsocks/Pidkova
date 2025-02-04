plugins {
    alias(libs.plugins.pidkova.jvm.library)
    alias(libs.plugins.realm)
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":repository"))
    implementation(project(":domain"))

    implementation(libs.realm.kotlin)
}