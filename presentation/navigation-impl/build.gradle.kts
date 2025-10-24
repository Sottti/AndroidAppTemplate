import com.sottti.android.app.template.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.sottti.android.app.template.presentation.navigation.impl"
}

dependencies {
    implementation(libs.hilt)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.lifecycle.viewModel.navigation)
    implementation(libs.navigation.runtime)
    implementation(libs.navigation.ui)
    implementation(project(module.presentation.itemDetailFeature))
    implementation(project(module.presentation.navigation))
    implementation(project(module.presentation.pullyListFeature))
    ksp(libs.hilt.compiler)
}
