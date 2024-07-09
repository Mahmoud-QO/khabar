package com.example.khabar.presentation.viewmodel.onboarding

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khabar.domain.usecase.OnboardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
	private val onboardingUseCase: OnboardingUseCase
) : ViewModel() {

	var navigateToHome by mutableStateOf(true); private set

	fun onEvent(onboardingEvent: OnboardingEvent) {
		when (onboardingEvent) {
			OnboardingEvent.ClickGetStarted -> completeOnboarding()
			OnboardingEvent.ClickSkip -> completeOnboarding()
		}
	}

	private fun completeOnboarding() = viewModelScope.launch {
		onboardingUseCase.completeOnboarding()
		navigateToHome = true
	}
}