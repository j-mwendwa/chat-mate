package com.example.chatmate.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel

private val DarkColorScheme = darkColorScheme(
    primary = MetaBlue,
    secondary = MetaPurple,
    tertiary = MetaWhite,
    background = MetaBlack,
    onPrimary = MetaWhite,
    onSecondary = MetaWhite,
    onTertiary = MetaBlack,
    onBackground = MetaWhite,
    surface = MetaBlack,
    onSurface = MetaWhite
)

private val LightColorScheme = lightColorScheme(
    primary = MetaBlue,
    secondary = MetaPurple,
    tertiary = MetaBlack,
    background = MetaWhite,
    onPrimary = MetaBlack,
    onSecondary = MetaBlack,
    onTertiary = MetaWhite,
    onBackground = MetaBlack,
    surface = MetaWhite,
    onSurface = MetaBlack
)

@Composable
fun ChatMateTheme(
    themeViewModel: ThemeViewModel = viewModel(factory = ThemeViewModelFactory(LocalContext.current)),
    content: @Composable () -> Unit
) {
    val isDarkTheme by themeViewModel.isDarkTheme.collectAsState(initial = isSystemInDarkTheme())

    val colorScheme = if (isDarkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDarkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}