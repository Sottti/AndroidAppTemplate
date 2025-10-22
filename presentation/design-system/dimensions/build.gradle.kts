plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.sottti.android.app.template.presentation.design.system.dimensions"

}

dependencies {
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.compose.runtime)
    implementation(libs.compose.ui)
    implementation(platform(libs.compose.bom))
}
