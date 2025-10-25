package com.sottti.android.app.template.presentation.home.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.android.app.template.presentation.design.system.themes.AndroidAppTemplateTheme
import com.sottti.android.app.template.presentation.home.data.HomeViewModel
import com.sottti.android.app.template.presentation.navigation.impl.Navigator
import com.sottti.android.app.template.presentation.navigation.manager.NavigationManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal open class HomeActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var navigationManager: NavigationManager

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

public fun startHomeActivity(context: Context) {
    context.startActivity(Intent(context, HomeActivity::class.java))
}
