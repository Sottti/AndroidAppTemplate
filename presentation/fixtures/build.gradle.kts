import com.sottti.android.app.template.buildsrc.module

plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.sottti.android.app.template.presentation.fixtures"
}

dependencies {
    implementation(project(module.domain.coreModels))
}
