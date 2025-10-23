import com.sottti.android.app.template.buildSrc.module


plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.sottti.android.app.template.presentation.design.system.icon.resources"
}

dependencies {
    implementation(libs.appcompat)
    implementation(project(module.presentation.designSystem.colors))
}
