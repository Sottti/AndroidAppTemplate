import com.sottti.android.app.template.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.sottti.android.app.template.data.network"
}

dependencies {
    api(libs.ktor.client.core)
    api(libs.result)
    implementation(libs.coil.network.ktor3)
    implementation(libs.coil.test)
    implementation(libs.hilt)
    implementation(libs.hilt.android.testing)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.mock)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(project(module.presentation.designSystem.images))
    ksp(libs.hilt.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.ktor.client.mock)
    testImplementation(libs.truth)
}
