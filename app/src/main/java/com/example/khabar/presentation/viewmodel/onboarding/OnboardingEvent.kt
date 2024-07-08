package com.example.khabar.presentation.viewmodel.onboarding

sealed class OnboardingEvent {
    data object ClickGetStarted : OnboardingEvent()

}