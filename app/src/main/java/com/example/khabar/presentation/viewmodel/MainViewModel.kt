package com.example.khabar.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khabar.domain.usecase.AppEntryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
	private val appEntryUseCase: AppEntryUseCase
) : ViewModel() {

    private val _splashScreenCondition = MutableStateFlow(true)
    val splashScreenCondition = _splashScreenCondition.asStateFlow()

    private val _isOnboardingCompleted = MutableStateFlow(false)
    val isOnboardingCompleted = _isOnboardingCompleted.asStateFlow()

    init {
        viewModelScope.launch {
            appEntryUseCase.getOnboardingStatus().collect {
                _isOnboardingCompleted.value = it
                delay(500)
                _splashScreenCondition.value = false
            }
        }
    }

}