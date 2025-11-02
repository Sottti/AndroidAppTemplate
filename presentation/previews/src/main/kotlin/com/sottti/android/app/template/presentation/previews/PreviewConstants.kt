package com.sottti.android.app.template.presentation.previews

private object Pixel10XlPro {
    const val HEIGHT_PX = "2992px"
    const val WIDTH_PX = "1344px"
    const val DPI = 486
}

private const val PIXEL_10_PRO_XL_SPEC_PORTRAIT =
    "spec:width=${Pixel10XlPro.WIDTH_PX},height=${Pixel10XlPro.HEIGHT_PX},dpi=${Pixel10XlPro.DPI}"
private const val PIXEL_10_PRO_XL_SPEC_LANDSCAPE =
    "spec:width=${Pixel10XlPro.HEIGHT_PX},height=${Pixel10XlPro.WIDTH_PX},dpi=${Pixel10XlPro.DPI}"

internal const val DARK_THEME_GROUP = "Dark Theme"
internal const val DARK_THEME_LANDSCAPE_NAME = "4. Dark - Landscape"
internal const val DARK_THEME_PORTRAIT_NAME = "3. Dark - Portrait"
internal const val DEVICE_LANDSCAPE = PIXEL_10_PRO_XL_SPEC_LANDSCAPE
internal const val DEVICE_PORTRAIT = PIXEL_10_PRO_XL_SPEC_PORTRAIT
internal const val LIGHT_THEME_GROUP = "Light Theme"
internal const val LIGHT_THEME_LANDSCAPE_NAME = "2. Light - Landscape"
internal const val LIGHT_THEME_PORTRAIT_NAME = "1. Light - Portrait"
