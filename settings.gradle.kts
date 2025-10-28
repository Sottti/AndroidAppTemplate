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
    ":data:items",
    ":data:network",
    ":data:settings",
    ":data:system-features",
    ":di",
    ":domain:core-models",
    ":domain:items",
    ":domain:settings",
    ":domain:system-features",
    ":presentation:design-system:colors",
    ":presentation:design-system:dimensions",
    ":presentation:design-system:icon-resources",
    ":presentation:design-system:images",
    ":presentation:design-system:progress-indicators",
    ":presentation:design-system:shapes",
    ":presentation:design-system:text",
    ":presentation:design-system:themes",
    ":presentation:design-system:top-bars",
    ":presentation:design-system:typography",
    ":presentation:features:home",
    ":presentation:features:item-detail",
    ":presentation:features:items-list",
    ":presentation:fixtures",
    ":presentation:navigation",
    ":presentation:navigation-impl",
    ":presentation:paparazzi",
    ":presentation:previews",
    ":presentation:utils",
    ":utils:lifecycle",
)
