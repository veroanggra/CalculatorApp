package com.veroanggra.calculatorapp.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.veroanggra.calculatorapp.ui.component.CustomButtonColorGuideline

private val LightColorScheme = CustomButtonColorGuideline(
    colorScheme = lightColorScheme(),
    clearButtonColor = lightYellow,
    numberButtonColor = lightTransparentYellow,
    operationButtonColor = lightTransparentPurple,
    calculateButtonColor = lightPurple,
    backgroundColor = lightBackground,
    fontColorPurple = lightFontPurple,
    fontColorYellow = lightFontYellow
)

private val DarkColorScheme = CustomButtonColorGuideline(
    colorScheme = darkColorScheme(),
    clearButtonColor = darkYellow,
    numberButtonColor = darkTransparentYellow,
    operationButtonColor = darkTransparentPurple,
    calculateButtonColor = darkPurple,
    backgroundColor = darkBackground,
    fontColorPurple = darkFontPurple,
    fontColorYellow = darkFontYellow
)
val LocalColors = staticCompositionLocalOf { LightColorScheme }

@Composable
fun CalculatorAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.backgroundColor.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars =
                !darkTheme
        }
    }
    CompositionLocalProvider(LocalColors provides colorScheme) {
        MaterialTheme(
            colorScheme = colorScheme.colorScheme,
            typography = Typography,
            content = content
        )
    }
}