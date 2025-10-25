plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.sottti.android.app.template.presentation.previews"

}

dependencies {
    implementation(libs.compose.ui.tooling)
    implementation(platform(libs.compose.bom))
}
