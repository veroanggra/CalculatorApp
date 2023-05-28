package com.veroanggra.calculatorapp.ui.component

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import com.veroanggra.calculatorapp.ui.theme.LocalColors


data class CustomButtonColorGuideline(
    val colorScheme: ColorScheme,
    val clearButtonColor: Color,
    val numberButtonColor: Color,
    val operationButtonColor: Color,
    val calculateButtonColor: Color,
    val backgroundColor: Color,
    val fontColorYellow: Color,
    val fontColorPurple: Color
)

val MaterialTheme.myColors: CustomButtonColorGuideline
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current