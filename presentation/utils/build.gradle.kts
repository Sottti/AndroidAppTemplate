plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.sottti.android.app.template.presentation.utils"
}

dependencies {
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.compose.ui)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.lifecycle.viewModel)
    implementation(platform(libs.compose.bom))
}
