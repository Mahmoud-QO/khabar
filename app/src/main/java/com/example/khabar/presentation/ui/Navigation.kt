package com.example.khabar.presentation.ui

import kotlinx.serialization.Serializable

sealed class Route {

    @Serializable
    data object OnboardingScreen: Route()

    @Serializable
    data object HomeScreen: Route()

    @Serializable
    data object SearchScreen: Route()

    @Serializable
    data object BookmarkScreen: Route()

    @Serializable
    data class DetailsScreen(val id: Int): Route()
}