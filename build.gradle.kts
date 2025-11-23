import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import io.gitlab.arturbosch.detekt.report.ReportMergeTask
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

val libraries = the<org.gradle.accessors.dm.LibrariesForLibs>()

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

        // Ensure the build continues so the merge task can run later
        ignoreFailures = true

        // This is causing issues to Detekt
        exclude("**/StateInViewModelWhileSubscribed.kt")

        reports {
            html.required.set(true)
            xml.required.set(true)
            sarif.required.set(true)
            txt.required.set(false)
        }

        val detektTask = this
        rootProject.tasks.withType<ReportMergeTask>().configureEach {
            if (name == "mergeDetektSarif") {
                input.from(detektTask.reports.sarif.outputLocation)
            }
        }
    }

    tasks.withType<DetektCreateBaselineTask>().configureEach {
        jvmTarget = "17"

        // This is causing issues to Detekt
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
        sarifReport = true
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
            sarifReport = true
        }
    }
}

private fun compileSdk() = libs.versions.compileSdk.get().toInt()
private fun minSdk() = libs.versions.minSdk.get().toInt()
private fun targetSdk() = libs.versions.targetSdk.get().toInt()

tasks.register<Delete>("cleanPaparazziSnapshots") {
    group = "verification"
    description = "Deletes all Paparazzi snapshot images across the project."
    delete(fileTree(rootDir) { include("**/src/test/snapshots/images/**") })
}

tasks.register<ReportMergeTask>("mergeDetektSarif") {
    group = "verification"
    description = "Merges all subproject SARIF reports into one."
    output.set(layout.buildDirectory.file("reports/detekt/merge.sarif"))
}
