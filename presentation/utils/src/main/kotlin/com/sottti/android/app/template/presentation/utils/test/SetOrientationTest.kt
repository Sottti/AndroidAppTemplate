package com.sottti.android.app.template.presentation.utils.test

import android.content.pm.ActivityInfo
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule

public fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.setOrientation(
    orientation: OrientationTest,
) {
    when (orientation) {
        OrientationTest.Portrait -> setPortrait()
        OrientationTest.Landscape -> setLandscape()
    }
}

internal fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.setPortrait() {
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    waitForIdle()
}

internal fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.setLandscape() {
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    waitForIdle()
}
