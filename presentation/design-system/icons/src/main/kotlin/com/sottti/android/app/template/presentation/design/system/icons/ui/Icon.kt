package com.sottti.android.app.template.presentation.design.system.icons.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.sottti.android.app.template.presentation.design.system.icons.model.IconState
import com.sottti.android.app.template.presentation.design.system.themes.AndroidAppTemplateTheme
import com.sottti.android.app.template.presentation.previews.AndroidAppTemplatePreview
import androidx.compose.material3.Icon as MaterialIcon

@Composable
public fun Icon(
    iconState: IconState,
    modifier: Modifier = Modifier,
    crossfade: Boolean = false,
    tint: Color? = null,
    onClick: (() -> Unit)? = null,
) {
    val iconModifier = modifier.size(24.dp)

    @Composable
    fun RenderIcon(state: IconState) {
        when {
            onClick != null -> IconButton(onClick = onClick) {
                tint?.let {
                    MaterialIcon(
                        contentDescription = stringResource(state.descriptionResId),
                        modifier = iconModifier,
                        painter = painterResource(state.resId),
                        tint = tint,
                    )
                } ?: MaterialIcon(
                    contentDescription = stringResource(state.descriptionResId),
                    modifier = iconModifier,
                    painter = painterResource(state.resId),
                )
            }

            else -> MaterialIcon(
                painter = painterResource(state.resId),
                contentDescription = stringResource(state.descriptionResId),
                modifier = iconModifier,
            )
        }
    }

    when {
        crossfade -> Crossfade(targetState = iconState) { targetState -> RenderIcon(targetState) }
        else -> RenderIcon(iconState)
    }
}

@Composable
@AndroidAppTemplatePreview
internal fun IconPreview(
    @PreviewParameter(IconStateProvider::class)
    state: com.sottti.android.app.template.presentation.design.system.icons.ui.IconState,
) {
    AndroidAppTemplateTheme {
        Icon(
            crossfade = state.crossfade,
            onClick = state.onClick,
            iconState = state.iconState,
        )
    }
}
