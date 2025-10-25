import com.sottti.android.app.template.buildSrc.module

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.sottti.android.app.template.app"

    defaultConfig {
        applicationId = "com.sottti.android.app.template"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.hilt)
    implementation(libs.material)
    implementation(libs.splashscreen)
    implementation(project(module.di))
    implementation(project(module.presentation.designSystem.iconResources))
    implementation(project(module.presentation.designSystem.themes))
    implementation(project(module.presentation.features.home))
    ksp(libs.hilt.compiler)
}
