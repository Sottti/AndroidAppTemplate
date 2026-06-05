plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.sottti.android.app.template.presentation.utils"

    testFixtures {
        enable = true
    }
}

dependencies {
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.compose.ui)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.lifecycle.viewModel)
    implementation(platform(libs.compose.bom))
    testFixturesImplementation(libs.compose.runtime)
    testFixturesImplementation(libs.compose.ui)
    testFixturesImplementation(libs.compose.ui.test.junit4)
    testFixturesImplementation(libs.junit)
    testFixturesImplementation(platform(libs.compose.bom))
}
