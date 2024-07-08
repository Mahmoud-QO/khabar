package com.example.khabar.presentation.viewmodel.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
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
    private val _state = MutableStateFlow(true)
    val state = _state.asStateFlow()

	fun onEvent(onboardingEvent: OnboardingEvent) {
		when (onboardingEvent) {
			is OnboardingEvent.ClickGetStarted -> onClickGetStarted()
		}
	}

	private fun onClickGetStarted() = viewModelScope.launch { onboardingUseCase.completeOnboarding }
}