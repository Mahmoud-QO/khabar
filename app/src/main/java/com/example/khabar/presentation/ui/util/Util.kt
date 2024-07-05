package com.example.khabar.presentation.ui.util

import kotlin.math.ln
import kotlin.math.roundToInt

fun calculateFontSize(screenWidth: Int): Int {
    val baseFontSize = 12
    val baseScreenWidth = 320
    val scalingFactor = 50

    return baseFontSize + roundToMultipleOfFour(
        scalingFactor * ln(screenWidth.toDouble() / baseScreenWidth)
    )
}

fun roundToMultipleOfFour(value: Double): Int {
    val roundedValue = value.roundToInt()
    val remainder = roundedValue % 4
    return when {
        remainder == 0 -> roundedValue
        remainder <= 2 -> roundedValue - remainder
        else -> roundedValue + (4 - remainder)
    }
}

fun main() {
    val screenWidth = 1200
    val fontSize = calculateFontSize(screenWidth)
    println("Font size: $fontSize")
}