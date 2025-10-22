import com.sottti.android.app.template.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.sottti.android.app.template"
}

dependencies {
    implementation(libs.compose.material)
    implementation(libs.material)
    implementation(libs.splashscreen)
    implementation(platform(libs.compose.bom))
    implementation(project(module.presentation.designSystem.colors))
    implementation(project(module.presentation.designSystem.dimensions))
    implementation(project(module.presentation.designSystem.iconResources))
    implementation(project(module.presentation.designSystem.shapes))
    implementation(project(module.presentation.designSystem.typography))
}
