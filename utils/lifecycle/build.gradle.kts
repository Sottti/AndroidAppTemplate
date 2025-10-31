plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.sottti.android.app.template.utils.lifecycle"
}

dependencies {
    implementation(libs.kotlin.coroutines.core)
    testImplementation(libs.compose.activity)
    testImplementation(libs.junit)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.robolectric)
    testImplementation(libs.test.core)
    testImplementation(libs.truth)
    testImplementation(libs.turbine)
}
