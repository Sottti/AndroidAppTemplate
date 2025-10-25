package com.sottti.android.app.template.presentation.previews

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    device = DEVICE,
    group = LIGHT_THEME_GROUP,
    heightDp = 1800,
    name = LIGHT_THEME_NAME,
)
@Preview(
    device = DEVICE,
    group = DARK_THEME_GROUP,
    heightDp = 1800,
    name = DARK_THEME_NAME,
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
)
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION)
public annotation class AndroidAppTemplatePreviewTall
