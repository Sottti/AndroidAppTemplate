import com.sottti.android.app.template.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.sottti.android.app.template.presentation.design.system.text"
}

dependencies {
    implementation(libs.compose.material)
    implementation(libs.compose.ui)
    implementation(platform(libs.compose.bom))
    implementation(project(module.presentation.designSystem.typography))
}
