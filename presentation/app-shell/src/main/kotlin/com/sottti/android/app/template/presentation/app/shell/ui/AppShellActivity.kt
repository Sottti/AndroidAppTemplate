package com.sottti.android.app.template.presentation.app.shell.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.android.app.template.presentation.design.system.themes.AndroidAppTemplateTheme
import com.sottti.android.app.template.presentation.app.shell.data.AppShellViewModel
import com.sottti.android.app.template.presentation.navigation.impl.Navigator
import com.sottti.android.app.template.presentation.navigation.manager.NavigationManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
public open class AppShellActivity : ComponentActivity() {

    private val viewModel: AppShellViewModel by viewModels()

    @Inject
    internal lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            viewModel.state.collectAsStateWithLifecycle().value.let { state ->
                AndroidAppTemplateTheme(
                    colorContrast = state.systemColorContrast,
                    dynamicColor = state.dynamicColor,
                    systemTheme = state.systemTheme,
                ) {
                    Navigator(navigationManager)
                }
            }
        }
    }
}

public fun startAppShellActivity(context: Context) {
    context.startActivity(Intent(context, AppShellActivity::class.java))
}
