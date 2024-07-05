package com.example.khabar.presentation.ui.screen.onboarding

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.khabar.R
import com.example.khabar.presentation.ui.theme.OnboardingPage1
import com.example.khabar.presentation.ui.theme.OnboardingPage2
import com.example.khabar.presentation.ui.theme.OnboardingPage3

data class OnboardingPage(
    val title: String,
    val description: String,
    val color: Color,
    @DrawableRes val image: Int
)

internal val onboardingPages = listOf(
    OnboardingPage(
        title = "Personalized News Feed",
        description = "Tailor your news experience according to your interests",
        color = OnboardingPage1,
        image = R.drawable.personalized_feed
    ),
    OnboardingPage(
        title = "Stay Updated with Breaking News",
        description = "Stay Updated with instant and crucial news updates",
        color = OnboardingPage2,
        image = R.drawable.breaking_news
    ),
    OnboardingPage(
        title = "Real-Time Updates from Around the World",
        description = "Get notified about real-time news updates and global coverage",
        color = OnboardingPage3,
        image = R.drawable.real_time_updates
    )
)