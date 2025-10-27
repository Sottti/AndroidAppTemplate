import com.sottti.android.app.template.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.sottti.android.app.template.presentation.design.system.colors"
}

dependencies {
    implementation(libs.compose.material)
    implementation(libs.compose.runtime)
    implementation(libs.compose.ui)
    implementation(libs.core)
    implementation(platform(libs.compose.bom))
    implementation(project(module.domain.coreModels))

    testImplementation(libs.junit)
    testImplementation(libs.truth)
}
