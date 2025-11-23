package com.sottti.android.app.template.presentation.paparazzi

import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode

public fun paparazzi(
    nightMode: NightMode,
    renderingMode: SessionParams.RenderingMode = SessionParams.RenderingMode.NORMAL,
): Paparazzi = Paparazzi(
    deviceConfig = pixel10ProXl.copy(nightMode = nightMode),
    renderingMode = renderingMode,
    showSystemUi = false,
    theme = "Theme.AndroidAppTemplate",
)

public fun nightModeParameters(): Collection<Array<Any>> =
    listOf(
        arrayOf(NightMode.NOTNIGHT),
        arrayOf(NightMode.NIGHT),
    )

public fun <T> nightModeParameters(
    states: Sequence<T>,
): Collection<Array<Any?>> =
    states.flatMap { state ->
        listOf(
            arrayOf(NightMode.NOTNIGHT, state),
            arrayOf(NightMode.NIGHT, state),
        )
    }.toList()
