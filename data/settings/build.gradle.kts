import com.sottti.android.app.template.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.sottti.android.app.template.data.settings"
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.hilt)
    implementation(project(module.domain.settings))
    implementation(project(module.domain.systemFeatures))
    implementation(project(module.utils.lifecycle))
    ksp(libs.hilt.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.truth)
    testImplementation(libs.turbine)
}
