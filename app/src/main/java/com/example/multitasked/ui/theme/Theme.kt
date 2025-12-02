package com.example.multitasked.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

// Custom vibrant color schemes
private val OceanColorScheme = LightColorScheme.copy(
    primary = OceanBlue,
    onPrimary = Color.White,
    secondary = OceanTeal,
    tertiary = OceanCyan,
    surface = OceanSurface,
    onSurface = OceanOnSurface,
    background = OceanSurface,
    onBackground = OceanOnSurface,
    surfaceContainer = OceanSurface
)

private val ForestColorScheme = LightColorScheme.copy(
    primary = ForestGreen,
    onPrimary = Color.White,
    secondary = ForestLime,
    tertiary = ForestYellow,
    surface = ForestSurface,
    onSurface = ForestOnSurface,
    background = ForestSurface,
    onBackground = ForestOnSurface,
    surfaceContainer = ForestSurface
)

private val SunsetColorScheme = LightColorScheme.copy(
    primary = SunsetOrange,
    onPrimary = Color.White,
    secondary = SunsetAmber,
    tertiary = SunsetRed,
    surface = SunsetSurface,
    onSurface = SunsetOnSurface,
    background = SunsetSurface,
    onBackground = SunsetOnSurface,
    surfaceContainer = SunsetSurface
)

// AppTheme enum to define available themes
enum class AppTheme {
    SYSTEM,
    LIGHT,
    DARK,
    OCEAN,
    FOREST,
    SUNSET
}

@Composable
fun MultiTaskedTheme(
    theme: AppTheme = AppTheme.SYSTEM,
    content: @Composable () -> Unit
) {
    val colorScheme = when (theme) {
        AppTheme.SYSTEM -> {
            val context = LocalContext.current
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (isSystemInDarkTheme()) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            } else {
                if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme
            }
        }
        AppTheme.LIGHT -> LightColorScheme
        AppTheme.DARK -> DarkColorScheme
        AppTheme.OCEAN -> OceanColorScheme
        AppTheme.FOREST -> ForestColorScheme
        AppTheme.SUNSET -> SunsetColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
