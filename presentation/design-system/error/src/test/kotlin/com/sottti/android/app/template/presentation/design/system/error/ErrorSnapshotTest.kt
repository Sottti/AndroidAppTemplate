package com.sottti.android.app.template.presentation.design.system.error

import com.android.resources.NightMode
import com.sottti.android.app.template.presentation.paparazzi.nightModeParameters
import com.sottti.android.app.template.presentation.paparazzi.paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class ErrorSnapshotTest(
    nightMode: NightMode,
    private val state: ErrorState?,
) {

    @get:Rule
    val paparazzi = paparazzi(nightMode)

    @Test
    fun snapshotTest() {
        paparazzi.snapshot {
            ErrorUiPreview(state)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any?>> =
            nightModeParameters(ErrorUiStateProvider().values)
    }
}
