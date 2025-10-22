import com.sottti.android.app.template.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.sottti.android.app.template"
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.hilt)
    implementation(project(module.domain.settings))
    implementation(project(module.domain.systemFeatures))
    ksp(libs.hilt.compiler)

    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.kotlin.coroutines.test)
    androidTestImplementation(libs.mockk.android) {
        exclude(group = "org.junit.jupiter")
        exclude(group = "org.junit.vintage")
    }
    androidTestImplementation(libs.truth)
    androidTestRuntimeOnly(libs.test.runner)

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.truth)
}
