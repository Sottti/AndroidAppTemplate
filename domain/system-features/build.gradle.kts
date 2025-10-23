plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(libs.annotations)

    testImplementation(libs.junit)
    testImplementation(libs.truth)
}
