import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import org.gradle.accessors.dm.LibrariesForLibs
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
    alias(libs.plugins.kover) apply true
    alias(libs.plugins.paparazzi) apply false
}

tasks.register<Delete>("cleanPaparazziSnapshots") {
    group = "verification"
    description = "Deletes all Paparazzi snapshot images across the project."
    delete(fileTree(rootDir) { include("**/src/test/snapshots/images/**") })
}

val libraries = the<LibrariesForLibs>()

subprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")

    dependencies {
        "detektPlugins"(libraries.detekt.compose)
        "detektPlugins"(libraries.detekt.formatting)
    }

    detekt {
        toolVersion = libraries.versions.detekt.get()
        config.setFrom(files("$rootDir/detekt.yml"))
        buildUponDefaultConfig = true
        autoCorrect = true
        parallel = true
        baseline = file("detekt-baseline.xml")
    }

    tasks.withType<Detekt>().configureEach {
        jvmTarget = "17"
        ignoreFailures = false
        exclude("**/StateInViewModelWhileSubscribed.kt")
        reports {
            html.required.set(true) // Human readable (open in browser)
            xml.required.set(true) // Machine readable (Checkstyle format)
            sarif.required.set(false) // Not needed locally
            txt.required.set(false)
        }
    }

    tasks.withType<DetektCreateBaselineTask>().configureEach {
        jvmTarget = "17"
        exclude("**/StateInViewModelWhileSubscribed.kt")
    }

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
    lint {
        baseline = file("lint-baseline.xml")
        abortOnError = true
        checkReleaseBuilds = false
        xmlReport = true
        htmlReport = true
    }
}

private fun Project.androidLibraryConfig() {
    extensions.configure<LibraryExtension> {
        compileSdk = compileSdk()
        defaultConfig { minSdk = minSdk() }
        lint {
            targetSdk = targetSdk()
            baseline = file("lint-baseline.xml")
            abortOnError = true
            xmlReport = true
            htmlReport = true
        }
    }
}

private fun compileSdk() = libs.versions.compileSdk.get().toInt()
private fun minSdk() = libs.versions.minSdk.get().toInt()
private fun targetSdk() = libs.versions.targetSdk.get().toInt()
