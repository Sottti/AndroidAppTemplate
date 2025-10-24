@file:Suppress("ClassName", "ConstPropertyName")

package com.sottti.android.app.template.buildSrc

object module {
    object presentation {
        const val home = ":presentation:home"
        const val itemDetailFeature = ":presentation:item-detail-feature"
        const val navigation = ":presentation:navigation"
        const val navigationImpl = ":presentation:navigation-impl"
        const val paparazzi = ":presentation:paparazzi"
        const val pullyListFeature = ":presentation:pully-list-feature"
        const val utils = ":presentation:utils"

        object designSystem {
            const val colors = ":presentation:design-system:colors"
            const val dimensions = ":presentation:design-system:dimensions"
            const val iconResources = ":presentation:design-system:icon-resources"
            const val shapes = ":presentation:design-system:shapes"
            const val text = ":presentation:design-system:text"
            const val themes = ":presentation:design-system:themes"
            const val typography = ":presentation:design-system:typography"
        }
    }

    object domain {
        const val coreModels = ":domain:core-models"
        const val entity = ":domain:entity"
        const val settings = ":domain:settings"
        const val systemFeatures = ":domain:system-features"
    }

    object data {
        const val entity = ":data:entity"
        const val network = ":data:network"
        const val settings = ":data:settings"
        const val systemFeatures = ":data:system-features"
    }

    object utils {
        const val lifecycle = ":utils:lifecycle"
    }

    const val di = ":di"
}
