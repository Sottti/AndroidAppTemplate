import com.sottti.android.app.template.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.sottti.android.app.template.presentation.images"
}

dependencies {
    api(project(module.domain.coreModels))
    implementation(libs.annotations)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.ktor3)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.compose.ui.tooling)
    implementation(platform(libs.compose.bom))
    implementation(project(module.presentation.designSystem.progressIndicators))
    implementation(project(module.presentation.designSystem.shapes))
    implementation(project(module.presentation.designSystem.themes))
    implementation(project(module.presentation.fixtures))
    implementation(project(module.presentation.previews))
    testImplementation(project(module.presentation.paparazzi))
}
