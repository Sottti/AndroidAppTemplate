package com.sottti.android.app.template.presentation.design.system.informative

import com.android.resources.NightMode
import com.sottti.android.app.template.presentation.paparazzi.nightModeParameters
import com.sottti.android.app.template.presentation.paparazzi.paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class InformativeSnapshotTest(
    nightMode: NightMode,
    private val state: InformativeState,
) {

    @get:Rule
    val paparazzi = paparazzi(nightMode)

    @Test
    fun snapshotTest() {
        paparazzi.snapshot {
            InformativeUiPreview(state)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            nightModeParameters(InformativeUiStateProvider().values)
    }
}
