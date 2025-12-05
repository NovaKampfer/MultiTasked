package com.example.multitasked.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
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

@Immutable
data class CustomColors(
    val boardCard: Color,
    val taskCard: Color
)

private val LocalCustomColors = staticCompositionLocalOf {
    CustomColors(
        boardCard = Color.Unspecified,
        taskCard = Color.Unspecified
    )
}

private val LightCustomColors = CustomColors(
    boardCard = LightBoardCard,
    taskCard = LightTaskCard
)

private val DarkCustomColors = CustomColors(
    boardCard = DarkBoardCard,
    taskCard = DarkTaskCard
)

private val OceanCustomColors = CustomColors(
    boardCard = OceanBoardCard,
    taskCard = OceanTaskCard
)

private val ForestCustomColors = CustomColors(
    boardCard = ForestBoardCard,
    taskCard = ForestTaskCard
)

private val SunsetCustomColors = CustomColors(
    boardCard = SunsetBoardCard,
    taskCard = SunsetTaskCard
)

object CustomTheme {
    val colors: CustomColors
        @Composable
        get() = LocalCustomColors.current
}

@Composable
fun MultiTaskedTheme(
    theme: AppTheme = AppTheme.SYSTEM,
    content: @Composable () -> Unit
) {
    val (colorScheme, customColors) = when (theme) {
        AppTheme.SYSTEM -> {
            val context = LocalContext.current
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (isSystemInDarkTheme()) dynamicDarkColorScheme(context) to DarkCustomColors
                else dynamicLightColorScheme(context) to LightCustomColors
            } else {
                if (isSystemInDarkTheme()) DarkColorScheme to DarkCustomColors
                else LightColorScheme to LightCustomColors
            }
        }
        AppTheme.LIGHT -> LightColorScheme to LightCustomColors
        AppTheme.DARK -> DarkColorScheme to DarkCustomColors
        AppTheme.OCEAN -> OceanColorScheme to OceanCustomColors
        AppTheme.FOREST -> ForestColorScheme to ForestCustomColors
        AppTheme.SUNSET -> SunsetColorScheme to SunsetCustomColors
    }

    CompositionLocalProvider(LocalCustomColors provides customColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}
