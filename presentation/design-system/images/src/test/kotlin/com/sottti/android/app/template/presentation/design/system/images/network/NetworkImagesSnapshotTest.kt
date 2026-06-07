package com.sottti.android.app.template.presentation.design.system.images.network

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.sottti.android.app.template.presentation.paparazzi.nightModeParameters
import com.sottti.android.app.template.presentation.paparazzi.paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class NetworkImagesSnapshotTest(
    nightMode: NightMode,
    private val state: NetworkImageState,
) {

    @get:Rule
    val paparazzi = paparazzi(nightMode, SessionParams.RenderingMode.SHRINK)

    @Test
    fun snapshotTest() {
        paparazzi.snapshot {
            CompositionLocalProvider(LocalInspectionMode provides true) {
                NetworkImagePreview(state)
            }
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            nightModeParameters(NetworkImageStateProvider().values)
    }
}
