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
    namespace = "com.sottti.android.app.template.presentation.item.details"
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    androidTestImplementation(libs.compose.ui.test.junit4)
    androidTestImplementation(libs.truth)
    debugImplementation(libs.compose.ui.test.manifest)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.compose.ui.tooling)
    implementation(libs.hilt)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.lifecycle.viewModel)
    implementation(platform(libs.compose.bom))
    implementation(project(module.domain.items))
    implementation(project(module.presentation.designSystem.colors))
    implementation(project(module.presentation.designSystem.dimensions))
    implementation(project(module.presentation.designSystem.error))
    implementation(project(module.presentation.designSystem.icons))
    implementation(project(module.presentation.designSystem.images))
    implementation(project(module.presentation.designSystem.progressIndicators))
    implementation(project(module.presentation.designSystem.text))
    implementation(project(module.presentation.designSystem.themes))
    implementation(project(module.presentation.designSystem.topBars))
    implementation(project(module.presentation.navigation))
    implementation(project(module.presentation.previews))
    implementation(project(module.presentation.utils))
    ksp(libs.hilt.compiler)
    testImplementation(libs.junit)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.truth)
    testImplementation(libs.turbine)
    testImplementation(project(module.presentation.paparazzi))
}
