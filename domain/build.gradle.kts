plugins {
    alias(libs.plugins.pidkova.jvm.library)
}

dependencies {
    implementation(project(":core:network"))
}