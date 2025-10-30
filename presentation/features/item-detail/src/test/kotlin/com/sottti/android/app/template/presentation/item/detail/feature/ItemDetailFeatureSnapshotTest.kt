package com.sottti.android.app.template.presentation.item.detail.feature

import com.android.resources.NightMode
import com.sottti.android.app.template.presentation.item.detail.model.ItemDetailState
import com.sottti.android.app.template.presentation.paparazzi.nightModeParameters
import com.sottti.android.app.template.presentation.paparazzi.paparazzi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class ItemDetailFeatureSnapshotTest(
    nightMode: NightMode,
    private val state: ItemDetailState,
) {

    @get:Rule
    val paparazzi = paparazzi(nightMode)

    @Test
    fun snapshotTest() {
        paparazzi.snapshot {

        }
    }

    companion object Companion {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> = nightModeParameters()
    }
}
