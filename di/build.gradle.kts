import com.sottti.android.app.template.buildsrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.sottti.android.app.template.di"
}

dependencies {
    implementation(libs.hilt)
    implementation(project(module.data.characters))
    implementation(project(module.data.network))
    implementation(project(module.data.settings))
    implementation(project(module.data.systemFeatures))
    implementation(project(module.domain.characters))
    implementation(project(module.domain.settings))
    implementation(project(module.domain.systemFeatures))
    ksp(libs.hilt.compiler)
}
