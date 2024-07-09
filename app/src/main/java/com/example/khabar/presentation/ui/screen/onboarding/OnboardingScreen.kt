package com.example.khabar.presentation.ui.screen.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.khabar.presentation.ui.Route
import com.example.khabar.presentation.viewmodel.onboarding.OnboardingEvent
import com.example.khabar.presentation.viewmodel.onboarding.OnboardingViewModel

@Composable
fun OnboardingScreen(
    navController: NavController,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    if (viewModel.navigateToHome) {
        navController.navigate(Route.HomeScreen)
    }

    OnboardingScreenContent(onEvent = viewModel::onEvent)
}

@Composable
private fun OnboardingScreenContent(onEvent: (OnboardingEvent) -> Unit) {
    OnboardingPager(onboardingPages, onEvent = onEvent)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun OnboardingScreenPreview() {
//    OnboardingScreenContent {}
}