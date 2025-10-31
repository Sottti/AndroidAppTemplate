package com.sottti.android.app.template.data.settings.datasource.local.managers

import android.content.Context
import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES

internal fun Context.getThemedContext(isNightMode: Boolean): Context {
    val newConfig = Configuration(resources.configuration)
    val nightMode = if (isNightMode) UI_MODE_NIGHT_YES else UI_MODE_NIGHT_NO
    newConfig.uiMode = (newConfig.uiMode and UI_MODE_NIGHT_MASK.inv()) or nightMode
    return createConfigurationContext(newConfig)
}
