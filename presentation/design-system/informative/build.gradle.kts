import com.sottti.android.app.template.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.sottti.android.app.template.presentation.design.system.informative"
}

dependencies {
    api(project(module.presentation.designSystem.illustrations))
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.compose.runtime)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
    implementation(platform(libs.compose.bom))
    implementation(project(module.presentation.designSystem.colors))
    implementation(project(module.presentation.designSystem.dimensions))
    implementation(project(module.presentation.designSystem.text))
    implementation(project(module.presentation.designSystem.themes))
    implementation(project(module.presentation.fixtures))
    implementation(project(module.presentation.previews))
    implementation(project(module.presentation.utils))
    testImplementation(project(module.presentation.paparazzi))
}
