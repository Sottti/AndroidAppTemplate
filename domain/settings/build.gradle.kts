import com.sottti.android.app.template.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.sottti.android.app.template.domain.settings"
}

dependencies {
    api(project(module.domain.coreModels))
    implementation(libs.annotations)
    implementation(libs.hilt)
    implementation(libs.javax.inject)
    implementation(libs.kotlin.coroutines.core)
    ksp(libs.hilt.compiler)
    testImplementation(libs.junit)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.truth)
    testImplementation(libs.turbine)
}
