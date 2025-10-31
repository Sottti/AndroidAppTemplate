package com.sottti.android.app.template.presentation.design.system.icons

import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.sottti.android.app.template.presentation.design.system.icons.ui.IconPreview
import com.sottti.android.app.template.presentation.design.system.icons.ui.IconState
import com.sottti.android.app.template.presentation.design.system.icons.ui.IconStateProvider
import com.sottti.android.app.template.presentation.paparazzi.nightModeParameters
import com.sottti.android.app.template.presentation.paparazzi.paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class IconSnapshotTest(
    nightMode: NightMode,
    private val state: IconState,
) {

    @get:Rule
    val paparazzi = paparazzi(nightMode, SessionParams.RenderingMode.SHRINK)

    @Test
    fun snapshotTest() {
        paparazzi.snapshot {
            IconPreview(state)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            nightModeParameters(IconStateProvider().values)
    }
}
