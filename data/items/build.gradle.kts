import com.sottti.android.app.template.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.sottti.android.app.template.data.items"
}

dependencies {
    implementation(libs.hilt)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.paging.runtime)
    implementation(libs.room)
    implementation(libs.room.paging)
    implementation(project(module.data.network))
    implementation(project(module.domain.items))
    ksp(libs.hilt.compiler)
    ksp(libs.room.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.paging.testing)
    testImplementation(libs.robolectric)
    testImplementation(libs.test.core)
    testImplementation(libs.truth)
}
