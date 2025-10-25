import com.sottti.android.app.template.buildSrc.module

plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    api(project(module.domain.coreModels))
    implementation(libs.javax.inject)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.paging.common)
}
