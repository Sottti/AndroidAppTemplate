package com.sottti.android.app.template.app

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.sottti.android.app.template.presentation.home.ui.HomeActivity
import com.sottti.android.app.template.presentation.utils.test.OrientationTest
import com.sottti.android.app.template.presentation.utils.test.setOrientation
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal abstract class IntegrationTest(
    private val orientation: OrientationTest,
) {
    @get:Rule(order = 0)
    val hilt = HiltAndroidRule(this)
    @get:Rule(order = 1)
    val rule = createAndroidComposeRule<HomeActivity>()

    @Before
    fun setup() {
        hilt.inject()
        rule.setOrientation(orientation)
        rule.waitForIdle()
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "orientation={0}")
        fun data(): Collection<Array<Any>> = listOf(
            arrayOf(OrientationTest.Portrait as Any),
            arrayOf(OrientationTest.Landscape as Any),
        )
    }
}
