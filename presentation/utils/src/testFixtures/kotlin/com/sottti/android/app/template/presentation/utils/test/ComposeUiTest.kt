package com.sottti.android.app.template.presentation.utils.test

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.DeviceConfigurationOverride
import androidx.compose.ui.test.FontScale
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

private typealias UiTestRule =
    AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>

@RunWith(Parameterized::class)
public abstract class ComposeUiTest(
    public val fontScale: FontScalesTest,
    public val orientation: OrientationTest,
) {
    @get:Rule
    public val rule: UiTestRule = createAndroidComposeRule<ComponentActivity>()

    public inline fun runUiTest(
        noinline content: (@Composable () -> Unit),
        crossinline block: UiTestRule.() -> Unit,
    ) {
        rule.setOrientation(orientation)
        rule.setContent {
            DeviceConfigurationOverride(DeviceConfigurationOverride.FontScale(fontScale.scale)) {
                CompositionLocalProvider(LocalInspectionMode provides true) {
                    content()
                }
            }
        }
        rule.block()
    }

    public companion object Companion {
        @JvmStatic
        @Parameterized.Parameters(name = "fontScale={0}, orientation={1}")
        public fun data(): Collection<Array<Any>> = listOf(
            arrayOf(FontScalesTest.Normal as Any, OrientationTest.Portrait as Any),
            arrayOf(FontScalesTest.Normal as Any, OrientationTest.Landscape as Any),
            arrayOf(FontScalesTest.Huge as Any, OrientationTest.Portrait as Any),
            arrayOf(FontScalesTest.Huge as Any, OrientationTest.Landscape as Any),
        )
    }
}
