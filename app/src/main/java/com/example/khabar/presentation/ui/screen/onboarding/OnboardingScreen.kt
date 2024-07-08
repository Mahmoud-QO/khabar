package com.example.khabar.presentation.ui.screen.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.khabar.presentation.viewmodel.onboarding.OnboardingEvent
import com.example.khabar.presentation.viewmodel.onboarding.OnboardingViewModel

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    OnboardingScreenContent(state = state, onEvent = viewModel::onEvent)
}

@Composable
private fun OnboardingScreenContent(
    state: Boolean,
    onEvent: (OnboardingEvent) -> Unit
) {
    val initialPage = if(state) 2 else 0
    OnboardingPager(
        pages = onboardingPages, initialPage = initialPage
    ) { onEvent(OnboardingEvent.ClickGetStarted) }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun OnboardingScreenPreview() {
//    OnboardingScreenContent()
}