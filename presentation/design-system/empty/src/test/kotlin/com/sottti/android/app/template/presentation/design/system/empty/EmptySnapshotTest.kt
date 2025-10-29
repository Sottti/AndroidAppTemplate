package com.sottti.android.app.template.presentation.design.system.empty

import com.android.resources.NightMode
import com.sottti.android.app.template.presentation.paparazzi.nightModeParameters
import com.sottti.android.app.template.presentation.paparazzi.paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class EmptySnapshotTest(
    nightMode: NightMode,
    private val state: EmptyState?,
) {

    @get:Rule
    val paparazzi = paparazzi(nightMode)

    @Test
    fun snapshotTest() {
        paparazzi.snapshot {
            EmptyUiPreview(state)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            nightModeParameters(EmptyUiStateProvider().values)
    }
}
