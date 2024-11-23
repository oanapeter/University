package com.example.lab2.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFA86BB5),   // Softer pinkish purple for primary color in dark mode
    secondary = Color(0xFF8A2BE2), // Deeper purple for secondary in dark mode
    tertiary = Color(0xFFFFB3C1)    // Light pink accent color
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFCE8EDB),   // Softer pinkish purple for primary in light mode
    secondary = Color(0xFF8A2BE2), // Similar purple for secondary in light mode
    tertiary = Color(0xFFFFB3C1)    // Light pink accent color
)

@Composable
fun Lab2Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
