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
    implementation(libs.hilt)
    implementation(project(module.data.settings))
    implementation(project(module.data.systemFeatures))
    implementation(project(module.domain.settings))
    implementation(project(module.domain.systemFeatures))
    ksp(libs.hilt.compiler)
}
