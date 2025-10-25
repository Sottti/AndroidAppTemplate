import com.sottti.android.app.template.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.sottti.android.app.template.presentation.fixtures"
}

dependencies {
    implementation(project(module.domain.coreModels))
}
