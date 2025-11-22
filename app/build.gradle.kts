import com.sottti.android.app.template.buildSrc.module

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.sottti.android.app.template.app"

    defaultConfig {
        applicationId = "com.sottti.android.app.template"
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "com.sottti.android.app.template.app.HiltTestRunner"
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
    androidTestImplementation(libs.compose.ui.test.junit4) {
        // Exclude the transitive 'runner:1.5.0' which imposes a strict version constraint,
        // blocking our upgrade to runner:1.7.0'.
        exclude(group = "androidx.test", module = "runner")
    }
    androidTestImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.test.core)
    androidTestImplementation(libs.test.runner)
    debugImplementation(libs.compose.ui.test.manifest)
    implementation(libs.compose.runtime)
    implementation(libs.appcompat)
    implementation(libs.hilt)
    implementation(libs.material)
    implementation(libs.splashscreen)
    implementation(platform(libs.compose.bom))
    implementation(project(module.di))
    implementation(project(module.domain.items))
    implementation(project(module.presentation.designSystem.iconResources))
    implementation(project(module.presentation.designSystem.themes))
    implementation(project(module.presentation.designSystem.topBars))
    implementation(project(module.presentation.features.home))
    implementation(project(module.presentation.features.itemDetails))
    implementation(project(module.presentation.features.itemsList))
    ksp(libs.hilt.compiler)
    kspAndroidTest(libs.hilt.compiler)



    implementation(project(module.presentation.utils))
}
