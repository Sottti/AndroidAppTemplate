import com.sottti.android.app.template.buildSrc.module

plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(libs.annotations)
    implementation(libs.javax.inject)
    implementation(libs.kotlin.coroutines.core)

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.mockk)
    testImplementation(libs.truth)
    testImplementation(libs.turbine)
}
