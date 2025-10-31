plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.sottti.android.app.template.presentation.navigation"
}

dependencies {
    api(libs.navigation.runtime)
    ksp(libs.hilt.compiler)
}
