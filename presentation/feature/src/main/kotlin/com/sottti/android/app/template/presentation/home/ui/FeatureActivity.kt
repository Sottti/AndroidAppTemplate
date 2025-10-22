package com.sottti.android.app.template.presentation.home.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sottti.android.app.template.domain.settings.model.SystemTheme.DarkSystemTheme
import com.sottti.android.app.template.presentation.design.system.themes.AndroidAppTemplateTheme
import com.sottti.android.app.template.presentation.home.data.FeatureViewModel
import com.sottti.android.app.template.presentation.home.model.unwrap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal open class FeatureActivity : ComponentActivity() {

    private val viewModel: FeatureViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            viewModel.state.collectAsStateWithLifecycle().value.let { state ->
                AndroidAppTemplateTheme(
                    dynamicColor = state.themeProperties.dynamicColor.enabled,
                    isSystemInDarkTheme = state.themeProperties.systemTheme == DarkSystemTheme,
                ) {
                    HomeUi(state = state.unwrap(), onAction = viewModel.onAction)
                }
            }
        }
    }
}

public fun startHomeActivity(context: Context) {
    context.startActivity(Intent(context, FeatureActivity::class.java))
}
