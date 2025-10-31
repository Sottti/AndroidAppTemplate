import com.sottti.android.app.template.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.sottti.android.app.template.presentation.item.details.feature"
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.compose.material)
    implementation(libs.hilt)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.lifecycle.viewModel)
    implementation(platform(libs.compose.bom))
    implementation(project(module.domain.items))
    implementation(project(module.presentation.designSystem.colors))
    implementation(project(module.presentation.designSystem.dimensions))
    implementation(project(module.presentation.designSystem.icons))
    implementation(project(module.presentation.designSystem.progressIndicators))
    implementation(project(module.presentation.designSystem.text))
    implementation(project(module.presentation.designSystem.themes))
    implementation(project(module.presentation.designSystem.topBars))
    implementation(project(module.presentation.navigation))
    implementation(project(module.presentation.utils))
    ksp(libs.hilt.compiler)
    testImplementation(project(module.presentation.paparazzi))
}
