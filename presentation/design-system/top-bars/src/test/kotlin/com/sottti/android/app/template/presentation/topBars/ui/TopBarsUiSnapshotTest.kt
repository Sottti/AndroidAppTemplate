package com.sottti.android.app.template.presentation.topBars.ui

import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.sottti.android.app.template.presentation.design.system.top.bars.ui.MainTopBarPreview
import com.sottti.android.app.template.presentation.design.system.top.bars.ui.MainTopBarState
import com.sottti.android.app.template.presentation.design.system.top.bars.ui.MainTopBarUiStateProvider
import com.sottti.android.app.template.presentation.paparazzi.nightModeParameters
import com.sottti.android.app.template.presentation.paparazzi.paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class TopBarsUiSnapshotTest(
    nightMode: NightMode,
    private val state: MainTopBarState,
) {

    @get:Rule
    val paparazzi = paparazzi(nightMode, SessionParams.RenderingMode.SHRINK)

    @Test
    fun snapshotTest() {
        paparazzi.snapshot {
            MainTopBarPreview(state)
        }
    }

    companion object Companion {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            nightModeParameters(MainTopBarUiStateProvider().values)
    }
}
