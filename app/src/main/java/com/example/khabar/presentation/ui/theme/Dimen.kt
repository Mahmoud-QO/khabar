package com.example.khabar.presentation.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val extraSmall: Dp,
    val small: Dp,
    val medium: Dp,
    val large: Dp,
    val extraLarge: Dp
)


// TODO: change the values

val compactDimensions = Dimensions(
    extraSmall = 4.dp,
    small = 8.dp,
    medium = 16.dp,
    large = 32.dp,
    extraLarge = 64.dp
)

val mediumDimensions = Dimensions(
    extraSmall = 8.dp,
    small = 16.dp,
    medium = 24.dp,
    large = 40.dp,
    extraLarge = 80.dp
)

val expandedDimensions = Dimensions(
    extraSmall = 16.dp,
    small = 32.dp,
    medium = 48.dp,
    large = 64.dp,
    extraLarge = 120.dp
)


