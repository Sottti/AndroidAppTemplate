package com.sottti.android.app.template.data.settings.managers

import android.app.UiModeManager
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sottti.android.app.template.domain.system.features.SystemFeatures
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class ThemeManagerTest {

    private lateinit var context: Context
    private lateinit var features: SystemFeatures
    private lateinit var manager: ThemeManager
    private lateinit var uiModeManager: UiModeManager

    @Before
    fun setup() {
        context = mockk()
        features = mockk()
        uiModeManager = mockk()
        manager = ThemeManager(context, features, uiModeManager)
        mockkStatic(AppCompatDelegate::class)
    }

    @After
    fun tearDown() {
        unmockkStatic(AppCompatDelegate::class)
    }
}
