package com.example.khabar.presentation.viewmodel.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khabar.domain.usecase.OnboardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
	private val onboardingUseCase: OnboardingUseCase
) : ViewModel() {

	private val _navigateToHome = MutableSharedFlow<Unit>()
	val navigateToHome = _navigateToHome.asSharedFlow()

	fun onEvent(onboardingEvent: OnboardingEvent) {
		when (onboardingEvent) {
			OnboardingEvent.ClickGetStarted -> completeOnboarding()
			OnboardingEvent.ClickSkip -> completeOnboarding()
		}
	}

	private fun completeOnboarding() = viewModelScope.launch {
		onboardingUseCase.completeOnboarding()
		_navigateToHome.emit(Unit)
	}
}