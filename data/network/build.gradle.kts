plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.sottti.android.app.template.data.network"

    testFixtures {
        enable = true
    }
}

dependencies {
    api(libs.ktor.client.core)
    api(libs.result)
    implementation(libs.coil.network.ktor3)
    implementation(libs.hilt)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.serialization.kotlinx.json)
    ksp(libs.hilt.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.ktor.client.mock)
    testImplementation(libs.truth)
    testFixturesImplementation(libs.coil.test)
    testFixturesImplementation(libs.hilt.android.testing)
    testFixturesImplementation(libs.kotlinx.serialization.json)
    testFixturesImplementation(libs.ktor.client.mock)
}
