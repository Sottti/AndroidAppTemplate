package com.sottti.android.app.template.presentation.design.system.dimensions.compositionLocal

import androidx.compose.runtime.staticCompositionLocalOf
import com.sottti.android.app.template.presentation.design.system.dimensions.model.Dimensions

@Suppress("TopLevelPropertyNaming")
internal val LocalDimensions = staticCompositionLocalOf<Dimensions> {
    error("No dimensions provided. Make sure to wrap your composables in a custom theme.")
}
