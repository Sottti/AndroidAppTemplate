package com.sottti.android.app.template.presentation.string.provider

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

public class StringProvider @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    public fun getString(resId: Int, vararg formatArgs: Any): String =
        context.getString(resId, *formatArgs)
}
