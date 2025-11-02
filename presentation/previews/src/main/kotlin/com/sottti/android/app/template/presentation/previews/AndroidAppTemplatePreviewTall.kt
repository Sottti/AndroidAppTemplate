package com.sottti.android.app.template.presentation.previews

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    device = DEVICE_PORTRAIT,
    group = LIGHT_THEME_GROUP,
    name = LIGHT_THEME_PORTRAIT_NAME,
)
@Preview(
    device = DEVICE_LANDSCAPE,
    group = LIGHT_THEME_GROUP,
    name = LIGHT_THEME_LANDSCAPE_NAME,
)
@Preview(
    device = DEVICE_PORTRAIT,
    group = DARK_THEME_GROUP,
    name = DARK_THEME_PORTRAIT_NAME,
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
)
@Preview(
    device = DEVICE_LANDSCAPE,
    group = DARK_THEME_GROUP,
    name = DARK_THEME_LANDSCAPE_NAME,
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
)
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION)
public annotation class AndroidAppTemplatePreviewTall
