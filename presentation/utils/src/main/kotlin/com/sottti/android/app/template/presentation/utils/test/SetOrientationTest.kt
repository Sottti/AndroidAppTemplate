package com.sottti.android.app.template.presentation.utils.test

import android.content.pm.ActivityInfo
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule

public fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.setPortrait() {
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    waitForIdle()
}

public fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.setLandscape() {
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    waitForIdle()
}
