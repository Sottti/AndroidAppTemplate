@file:Suppress("ClassName", "ConstPropertyName")

package com.sottti.android.app.template.buildSrc

object module {
    object presentation {
        object features {
            const val home = ":presentation:features:home"
            const val itemDetail = ":presentation:features:item-detail"
            const val itemsList = ":presentation:features:items-list"
        }

        const val fixtures = ":presentation:fixtures"
        const val navigation = ":presentation:navigation"
        const val navigationImpl = ":presentation:navigation-impl"
        const val paparazzi = ":presentation:paparazzi"
        const val previews = ":presentation:previews"
        const val utils = ":presentation:utils"

        object designSystem {
            const val colors = ":presentation:design-system:colors"
            const val dimensions = ":presentation:design-system:dimensions"
            const val iconResources = ":presentation:design-system:icon-resources"
            const val illustrations = ":presentation:design-system:illustrations"
            const val images = ":presentation:design-system:images"
            const val informative = ":presentation:design-system:informative"
            const val progressIndicators = ":presentation:design-system:progress-indicators"
            const val shapes = ":presentation:design-system:shapes"
            const val text = ":presentation:design-system:text"
            const val themes = ":presentation:design-system:themes"
            const val topBars = ":presentation:design-system:top-bars"
            const val typography = ":presentation:design-system:typography"
        }
    }

    object domain {
        const val coreModels = ":domain:core-models"
        const val items = ":domain:items"
        const val settings = ":domain:settings"
        const val systemFeatures = ":domain:system-features"
    }

    object data {
        const val items = ":data:items"
        const val network = ":data:network"
        const val settings = ":data:settings"
        const val systemFeatures = ":data:system-features"
    }

    object utils {
        const val lifecycle = ":utils:lifecycle"
    }

    const val di = ":di"
}
