package com.example.khabar.presentation.ui.theme

import android.content.res.Configuration
import androidx.compose.material3.Typography
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration

data class ThemeAdaptation(
    val dimensions: Dimensions,
    val typography: Typography
)

@Composable
fun rememberThemeAdaptation(
    widthSizeClass: WindowWidthSizeClass,
    heightSizeClass: WindowHeightSizeClass,
    configuration: Configuration = LocalConfiguration.current,
) = remember {
    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
        getThemeLandscapeAdaptation(heightSizeClass, configuration.screenHeightDp)
    else
        getThemePortraitAdaptation(widthSizeClass, configuration.screenWidthDp)
}

private fun getThemePortraitAdaptation(widthSizeClass: WindowWidthSizeClass, screenWidth: Int) =
    when (widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            if (screenWidth < 330) ThemeAdaptation(compactDimensions, xxSmallScreenTypography)
            else if (screenWidth < 360) ThemeAdaptation(compactDimensions, xSmallScreenTypography)
            else if (screenWidth < 480) ThemeAdaptation(compactDimensions, smallScreenTypography)
            else ThemeAdaptation(compactDimensions, normalScreenTypography)
        }

        WindowWidthSizeClass.Medium -> {
            if (screenWidth < 720) ThemeAdaptation(mediumDimensions, normalScreenTypography)
            else ThemeAdaptation(mediumDimensions, largeScreenTypography)
        }

        WindowWidthSizeClass.Expanded -> {
            if (screenWidth < 1080) ThemeAdaptation(expandedDimensions, largeScreenTypography)
            else if (screenWidth < 1280) ThemeAdaptation(expandedDimensions, xLargeScreenTypography)
            else ThemeAdaptation(expandedDimensions, xxLargeScreenTypography)
        }

        else -> { ThemeAdaptation(mediumDimensions, normalScreenTypography) }
    }

private fun getThemeLandscapeAdaptation(heightSizeClass: WindowHeightSizeClass, screenHeight: Int) =
    when (heightSizeClass) {
        WindowHeightSizeClass.Compact -> {
            if (screenHeight < 330) ThemeAdaptation(compactDimensions, smallScreenTypography)
            else if (screenHeight < 360) ThemeAdaptation(compactDimensions, xSmallScreenTypography)
            else if (screenHeight < 480) ThemeAdaptation(compactDimensions, smallScreenTypography)
            else ThemeAdaptation(compactDimensions, normalScreenTypography)
        }

        WindowHeightSizeClass.Medium -> {
            if (screenHeight < 720) ThemeAdaptation(mediumDimensions, normalScreenTypography)
            else ThemeAdaptation(mediumDimensions, largeScreenTypography)
        }

        WindowHeightSizeClass.Expanded -> {
            if (screenHeight < 1080) ThemeAdaptation(expandedDimensions, largeScreenTypography)
            else if (screenHeight < 1280) ThemeAdaptation(expandedDimensions, xLargeScreenTypography)
            else ThemeAdaptation(expandedDimensions, xxLargeScreenTypography)
        }

        else -> {
            ThemeAdaptation(mediumDimensions, normalScreenTypography)
        }
    }
