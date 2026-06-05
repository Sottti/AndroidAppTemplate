plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.android.app.template.presentation.string.provider"
}

dependencies {
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
}
