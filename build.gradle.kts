import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.detekt) apply true
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.ksp) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.paparazzi) apply false
}

detekt {
    toolVersion = libs.versions.detekt.get()
    config.from("$rootDir/detekt.yml")
    buildUponDefaultConfig = true
    autoCorrect = true
}

dependencies {
    detektPlugins(libs.detekt.formatting)
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    reports {
        html.required.set(true)
        xml.required.set(true)
        sarif.required.set(true)
        txt.required.set(false)
    }
}

dependencies {
    detektPlugins(libs.detekt.formatting)
}

tasks.register<Delete>("cleanPaparazziSnapshots") {
    delete(
        fileTree(rootDir) {
            include("**/src/test/snapshots/images/**")
        }
    )
}

subprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")

    plugins.withId("com.android.application") {
        configure<ApplicationExtension> { androidApplicationConfig() }
    }

    plugins.withId("com.android.library") {
        androidLibraryConfig()
    }

    plugins.withId("org.jetbrains.kotlin.android") {
        extensions.configure<KotlinAndroidProjectExtension> {
            explicitApi()
            jvmToolchain(17)
            compilerOptions {
                freeCompilerArgs.add("-Xwhen-guards")
                freeCompilerArgs.add("-Xcontext-parameters")
                freeCompilerArgs.add("-Xannotation-default-target=param-property")
            }
        }
    }

    plugins.withId("org.jetbrains.kotlin.jvm") {
        extensions.configure<KotlinJvmProjectExtension> {
            explicitApi()
            jvmToolchain(17)
            compilerOptions {
                freeCompilerArgs.add("-Xcontext-parameters")
            }
        }
    }
}

private fun ApplicationExtension.androidApplicationConfig() {
    compileSdk = compileSdk()

    defaultConfig {
        minSdk = minSdk()
        targetSdk = targetSdk()
    }
}

private fun Project.androidLibraryConfig() {
    extensions.configure<LibraryExtension> {
        compileSdk = compileSdk()
        defaultConfig { minSdk = minSdk() }
        lint { targetSdk = targetSdk() }
    }
}

private fun compileSdk() = libs.versions.compileSdk.get().toInt()
private fun minSdk() = libs.versions.minSdk.get().toInt()
private fun targetSdk() = libs.versions.targetSdk.get().toInt()
