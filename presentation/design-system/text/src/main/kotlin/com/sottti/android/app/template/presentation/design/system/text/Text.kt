package com.sottti.android.app.template.presentation.design.system.text

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.sottti.android.app.template.presentation.design.system.typography.compositionLocal.typography
import androidx.compose.material3.Text as MaterialText

@Stable
public object Text {

    public object Display {
        @Composable
        public fun Large(
            text: String,
            modifier: Modifier = Modifier,
            textAlign: TextAlign? = null,
            textColor: Color = Color.Unspecified,
        ) {
            ThemedText(
                text = text,
                textStyle = typography.displayLarge,
                textColor = textColor,
                modifier = modifier,
                textAlign = textAlign,
            )
        }

        @Composable
        public fun Large(
            @StringRes textResId: Int,
            modifier: Modifier = Modifier,
            textAlign: TextAlign? = null,
            textColor: Color = Color.Unspecified,
        ) {
            Large(
                text = stringResource(textResId),
                modifier = modifier,
                textAlign = textAlign,
                textColor = textColor
            )
        }

        @Composable
        public fun Medium(
            text: String,
            modifier: Modifier = Modifier,
            textAlign: TextAlign? = null,
            textColor: Color = Color.Unspecified,
        ) {
            ThemedText(
                text = text,
                textStyle = typography.displayMedium,
                textColor = textColor,
                modifier = modifier,
                textAlign = textAlign
            )
        }

        @Composable
        public fun Medium(
            @StringRes textResId: Int,
            modifier: Modifier = Modifier,
            textAlign: TextAlign? = null,
            textColor: Color = Color.Unspecified,
        ) {
            Medium(
                text = stringResource(textResId),
                modifier = modifier,
                textAlign = textAlign,
                textColor = textColor
            )
        }

        @Composable
        public fun Small(
            text: String,
            modifier: Modifier = Modifier,
            textAlign: TextAlign? = null,
            textColor: Color = Color.Unspecified,
        ) {
            ThemedText(
                text = text,
                textStyle = typography.displaySmall,
                textColor = textColor,
                modifier = modifier,
                textAlign = textAlign
            )
        }

        @Composable
        public fun Small(
            @StringRes textResId: Int,
            modifier: Modifier = Modifier,
            textAlign: TextAlign? = null,
            textColor: Color = Color.Unspecified,
        ) {
            Small(
                text = stringResource(textResId),
                modifier = modifier,
                textAlign = textAlign,
                textColor = textColor
            )
        }
    }

    public object Headline {
        @Composable
        public fun Large(
            text: String,
            modifier: Modifier = Modifier,
            textAlign: TextAlign? = null,
            textColor: Color = Color.Unspecified,
        ) {
            ThemedText(
                text = text,
                textStyle = typography.headlineLarge,
                textColor = textColor,
                modifier = modifier,
                textAlign = textAlign
            )
        }

        @Composable
        public fun Large(
            @StringRes textResId: Int,
            modifier: Modifier = Modifier,
            textAlign: TextAlign? = null,
            textColor: Color = Color.Unspecified,
        ) {
            Large(
                text = stringResource(textResId),
                modifier = modifier,
                textAlign = textAlign,
                textColor = textColor
            )
        }

        @Composable
        public fun Medium(
            text: String,
            modifier: Modifier = Modifier,
            textAlign: TextAlign? = null,
            textColor: Color = Color.Unspecified,
        ) {
            ThemedText(
                text = text,
                textStyle = typography.headlineMedium,
                textColor = textColor,
                modifier = modifier,
                textAlign = textAlign
            )
        }

        @Composable
        public fun Medium(
            @StringRes textResId: Int,
            modifier: Modifier = Modifier,
            textAlign: TextAlign? = null,
            textColor: Color = Color.Unspecified,
        ) {
            Medium(
                text = stringResource(textResId),
                modifier = modifier,
                textAlign = textAlign,
                textColor = textColor
            )
        }

        @Composable
        public fun Small(
            text: String,
            modifier: Modifier = Modifier,
            textAlign: TextAlign? = null,
            textColor: Color = Color.Unspecified,
        ) {
            ThemedText(
                text = text,
                textStyle = typography.headlineSmall,
                textColor = textColor,
                modifier = modifier,
                textAlign = textAlign
            )
        }

        @Composable
        public fun Small(
            @StringRes textResId: Int,
            modifier: Modifier = Modifier,
            textAlign: TextAlign? = null,
            textColor: Color = Color.Unspecified,
        ) {
            Small(
                text = stringResource(textResId),
                modifier = modifier,
                textAlign = textAlign,
                textColor = textColor
            )
        }
    }

    public object Title {
        @Composable
        public fun Large(
            text: String,
            modifier: Modifier = Modifier,
            textAlign: TextAlign? = null,
            textColor: Color = Color.Unspecified,
        ) {
            ThemedText(
                text = text,
                textStyle = typography.titleLarge,
                textColor = textColor,
                modifier = modifier,
                textAlign = textAlign
            )
        }

        @Composable
        public fun Large(
            @StringRes textResId: Int,
            modifier: Modifier = Modifier,
            textAlign: TextAlign? = null,
            textColor: Color = Color.Unspecified,
        ) {
            Large(
                text = stringResource(textResId),
                modifier = modifier,
                textAlign = textAlign,
                textColor = textColor
            )
        }

        @Composable
        public fun Medium(
            text: String,
            modifier: Modifier = Modifier,
            textAlign: TextAlign? = null,
            textColor: Color = Color.Unspecified,
        ) {
            ThemedText(
                text = text,
                textStyle = typography.titleMedium,
                textColor = textColor,
                modifier = modifier,
                textAlign = textAlign
            )
        }

        @Composable
        public fun Medium(
            @StringRes textResId: Int,
            modifier: Modifier = Modifier,
            textAlign: TextAlign? = null,
            textColor: Color = Color.Unspecified,
        ) {
            Medium(
                text = stringResource(textResId),
                modifier = modifier,
                textAlign = textAlign,
                textColor = textColor
            )
        }

        @Composable
        public fun Small(
            text: String,
            modifier: Modifier = Modifier,
            textAlign: TextAlign? = null,
            textColor: Color = Color.Unspecified,
        ) {
            ThemedText(
                text = text,
                textStyle = typography.titleSmall,
                textColor = textColor,
                modifier = modifier,
                textAlign = textAlign
            )
        }

        @Composable
        public fun Small(
            @StringRes textResId: Int,
            modifier: Modifier = Modifier,
            textAlign: TextAlign? = null,
            textColor: Color = Color.Unspecified,
        ) {
            Small(
                text = stringResource(textResId),
                modifier = modifier,
                textAlign = textAlign,
                textColor = textColor
            )
        }
    }

    public object Body {
        @Composable
        public fun Large(
            text: String,
            modifier: Modifier = Modifier,
            textAlign: TextAlign? = null,
            textColor: Color = Color.Unspecified,
        ) {
            ThemedText(
                text = text,
                textStyle = typography.bodyLarge,
                textColor = textColor,
                modifier = modifier,
                textAlign = textAlign
            )
        }

        @Composable
        public fun Large(
            @StringRes textResId: Int,
            modifier: Modifier = Modifier,
            textAlign: TextAlign? = null,
            textColor: Color = Color.Unspecified,
        ) {
            Large(
                text = stringResource(textResId),
                modifier = modifier,
                textAlign = textAlign,
                textColor = textColor
            )
        }

        @Composable
        public fun Medium(
            text: String,
            modifier: Modifier = Modifier,
            textAlign: TextAlign? = null,
            textColor: Color = Color.Unspecified,
        ) {
            ThemedText(
                text = text,
                textStyle = typography.bodyMedium,
                textColor = textColor,
                modifier = modifier,
                textAlign = textAlign
            )
        }

        @Composable
        public fun Medium(
            text: AnnotatedString,
            modifier: Modifier = Modifier,
            textAlign: TextAlign? = null,
            textColor: Color = Color.Unspecified,
        ) {
            ThemedText(
                text = text,
                textStyle = typography.bodyMedium,
                textColor = textColor,
                modifier = modifier,
                textAlign = textAlign
            )
        }


        @Composable
        public fun Medium(
            @StringRes textResId: Int,
            modifier: Modifier = Modifier,
            textAlign: TextAlign? = null,
            textColor: Color = Color.Unspecified,
        ) {
            Medium(
                text = stringResource(textResId),
                modifier = modifier,
                textAlign = textAlign,
                textColor = textColor
            )
        }

        @Composable
        public fun Small(
            text: String,
            modifier: Modifier = Modifier,
            textAlign: TextAlign? = null,
            textColor: Color = Color.Unspecified,
        ) {
            ThemedText(
                text = text,
                textStyle = typography.bodySmall,
                textColor = textColor,
                modifier = modifier,
                textAlign = textAlign
            )
        }

        @Composable
        public fun Small(
            @StringRes textResId: Int,
            modifier: Modifier = Modifier,
            textAlign: TextAlign? = null,
            textColor: Color = Color.Unspecified,
        ) {
            Small(
                text = stringResource(textResId),
                modifier = modifier,
                textAlign = textAlign,
                textColor = textColor
            )
        }
    }

    public object Label {
        @Composable
        public fun Large(
            text: String,
            modifier: Modifier = Modifier,
            textAlign: TextAlign? = null,
            textColor: Color = Color.Unspecified,
        ) {
            ThemedText(
                text = text,
                textStyle = typography.labelLarge,
                textColor = textColor,
                modifier = modifier,
                textAlign = textAlign
            )
        }

        @Composable
        public fun Large(
            @StringRes textResId: Int,
            modifier: Modifier = Modifier,
            textAlign: TextAlign? = null,
            textColor: Color = Color.Unspecified,
        ) {
            Large(
                text = stringResource(textResId),
                modifier = modifier,
                textAlign = textAlign,
                textColor = textColor
            )
        }

        @Composable
        public fun Medium(
            text: String,
            modifier: Modifier = Modifier,
            textAlign: TextAlign? = null,
            textColor: Color = Color.Unspecified,
        ) {
            ThemedText(
                text = text,
                textStyle = typography.labelMedium,
                textColor = textColor,
                modifier = modifier,
                textAlign = textAlign
            )
        }

        @Composable
        public fun Medium(
            @StringRes textResId: Int,
            modifier: Modifier = Modifier,
            textAlign: TextAlign? = null,
            textColor: Color = Color.Unspecified,
        ) {
            Medium(
                text = stringResource(textResId),
                modifier = modifier,
                textAlign = textAlign,
                textColor = textColor
            )
        }

        @Composable
        public fun Small(
            text: String,
            modifier: Modifier = Modifier,
            textAlign: TextAlign? = null,
            textColor: Color = Color.Unspecified,
        ) {
            ThemedText(
                text = text,
                textStyle = typography.labelSmall,
                textColor = textColor,
                modifier = modifier,
                textAlign = textAlign
            )
        }

        @Composable
        public fun Small(
            @StringRes textResId: Int,
            modifier: Modifier = Modifier,
            textAlign: TextAlign? = null,
            textColor: Color = Color.Unspecified,
        ) {
            Small(
                text = stringResource(textResId),
                modifier = modifier,
                textAlign = textAlign,
                textColor = textColor
            )
        }
    }

    @Composable
    public fun Vanilla(
        text: String,
        modifier: Modifier = Modifier,
        textAlign: TextAlign? = null,
    ) {
        MaterialText(
            text = text,
            modifier = modifier,
            textAlign = textAlign
        )
    }

    @Composable
    public fun Vanilla(
        @StringRes textResId: Int,
        modifier: Modifier = Modifier,
        textAlign: TextAlign? = null,
    ) {
        Vanilla(
            text = stringResource(textResId),
            modifier = modifier,
            textAlign = textAlign
        )
    }

    @Composable
    private fun ThemedText(
        text: String,
        textStyle: TextStyle,
        modifier: Modifier = Modifier,
        textColor: Color = Color.Unspecified,
        textAlign: TextAlign? = null,
    ) {
        MaterialText(
            text = text,
            style = textStyle,
            color = textColor,
            modifier = modifier,
            textAlign = textAlign
        )
    }

    @Composable
    private fun ThemedText(
        text: AnnotatedString,
        textStyle: TextStyle,
        textColor: Color,
        modifier: Modifier = Modifier,
        textAlign: TextAlign? = null,
    ) {
        MaterialText(
            text = text,
            style = textStyle,
            color = textColor,
            modifier = modifier,
            textAlign = textAlign
        )
    }
}
