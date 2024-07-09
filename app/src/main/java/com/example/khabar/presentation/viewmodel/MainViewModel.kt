package com.example.khabar.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.khabar.domain.usecase.AppEntryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
	private val appEntryUseCase: AppEntryUseCase
) : ViewModel() {

    var splashScreenCondition by mutableStateOf(true); private set
    var isOnboardingCompleted by mutableStateOf(false); private set

    init {
        appEntryUseCase.getOnboardingStatus().onEach {
            isOnboardingCompleted = it
            delay(500)
            splashScreenCondition = false
        }.launchIn(viewModelScope)
    }

}