package com.example.multitasked.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    secondary = DarkSecondary,
    tertiary = DarkTertiary
)

private val LightColorScheme = lightColorScheme(
    primary = LightPrimary,
    secondary = LightSecondary,
    tertiary = LightTertiary
)

// Custom vibrant color schemes
private val OceanColorScheme = darkColorScheme(
    primary = OceanPrimary,
    secondary = OceanSecondary,
    tertiary = OceanTertiary,
    background = OceanBackground,
    onBackground = OceanOnBackground,
    surface = OceanBackground,
    onSurface = OceanOnBackground,
    surfaceContainer = OceanCard,
    surfaceContainerLow = OceanCard,
    surfaceContainerHigh = OceanCard,
    surfaceContainerHighest = OceanCard,
    outline = OceanOnBackground.copy(alpha = 0.5f)
)

private val ForestColorScheme = darkColorScheme(
    primary = ForestPrimary,
    secondary = ForestSecondary,
    tertiary = ForestTertiary,
    background = ForestBackground,
    onBackground = ForestOnBackground,
    surface = ForestBackground,
    onSurface = ForestOnBackground,
    surfaceContainer = ForestCard,
    surfaceContainerLow = ForestCard,
    surfaceContainerHigh = ForestCard,
    surfaceContainerHighest = ForestCard,
    outline = ForestOnBackground.copy(alpha = 0.5f)
)

private val SunsetColorScheme = darkColorScheme(
    primary = SunsetPrimary,
    secondary = SunsetSecondary,
    tertiary = SunsetTertiary,
    background = SunsetBackground,
    onBackground = SunsetOnBackground,
    surface = SunsetBackground,
    onSurface = SunsetOnBackground,
    surfaceContainer = SunsetCard,
    surfaceContainerLow = SunsetCard,
    surfaceContainerHigh = SunsetCard,
    surfaceContainerHighest = SunsetCard,
    outline = SunsetOnBackground.copy(alpha = 0.5f)
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
                if (isSystemInDarkTheme()) dynamicDarkColorScheme(context)
                else dynamicLightColorScheme(context)
            } else {
                if (isSystemInDarkTheme()) DarkColorScheme
                else LightColorScheme
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
