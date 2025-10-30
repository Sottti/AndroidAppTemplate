package com.sottti.android.app.template.presentation.design.system.illustrations

import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.sottti.android.app.template.presentation.design.system.illustrations.ui.default.IllustrationPreview
import com.sottti.android.app.template.presentation.design.system.illustrations.ui.default.IllustrationPreviewState
import com.sottti.android.app.template.presentation.design.system.illustrations.ui.default.IllustrationUiStateProvider
import com.sottti.android.app.template.presentation.paparazzi.nightModeParameters
import com.sottti.android.app.template.presentation.paparazzi.paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class IllustrationSnapshotTest(
    nightMode: NightMode,
    val state: IllustrationPreviewState,
) {

    @get:Rule
    val paparazzi = paparazzi(nightMode, SessionParams.RenderingMode.SHRINK)

    @Test
    fun snapshotTest() {
        paparazzi.snapshot {
            IllustrationPreview(state)
        }
    }

    companion object Companion {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> = nightModeParameters(
            IllustrationUiStateProvider().values
        )
    }
}
