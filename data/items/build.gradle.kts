import com.sottti.android.app.template.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.sottti.android.app.template.data.items"
}

dependencies {
    implementation(libs.hilt)
    implementation(libs.paging.runtime)
    implementation(libs.room)
    implementation(libs.room.paging)
    implementation(project(module.domain.items))
    ksp(libs.hilt.compiler)
    ksp(libs.room.compiler)
}
