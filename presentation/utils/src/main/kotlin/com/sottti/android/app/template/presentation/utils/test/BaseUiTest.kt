package com.sottti.android.app.template.presentation.utils.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
public abstract class BaseUiTest(
    protected val orientation: OrientationTest,
) {
    @get:Rule
    public val rule: AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity> =
        createAndroidComposeRule<ComponentActivity>()

    public companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "orientation={0}")
        public fun data(): Collection<OrientationTest> = listOf(
            OrientationTest.Portrait,
            OrientationTest.Landscape,
        )
    }
}
