rootProject.name = "Android App Template"

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
include(
    ":app",
    ":data:entity",
    ":data:network",
    ":data:settings",
    ":data:system-features",
    ":di",
    ":domain:entity",
    ":domain:settings",
    ":domain:system-features",
    ":presentation:design-system:colors",
    ":presentation:design-system:dimensions",
    ":presentation:design-system:icon-resources",
    ":presentation:design-system:shapes",
    ":presentation:design-system:text",
    ":presentation:design-system:themes",
    ":presentation:design-system:typography",
    ":presentation:feature",
    ":presentation:paparazzi",
    ":presentation:utils",
    ":utils:lifecycle",
)
