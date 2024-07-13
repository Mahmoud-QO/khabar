package com.example.khabar.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.khabar.domain.model.NewsSource
import com.example.khabar.domain.usecase.AppEntryUseCase
import com.example.khabar.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase,
    private val appEntryUseCase: AppEntryUseCase
) : ViewModel() {

    private val _splashScreenCondition = MutableStateFlow(true)
    val splashScreenCondition = _splashScreenCondition.asStateFlow()

    private val _isOnboardingCompleted = MutableStateFlow(false)
    val isOnboardingCompleted = _isOnboardingCompleted.asStateFlow()

    private val _newsSources = MutableStateFlow(emptyList<NewsSource>())
    val newsSources = _newsSources.asStateFlow()

    val topHeadlines = newsUseCase.getTopHeadlines(
        listOf("google-news", "al-jazeera-english", "bbc-news", "cnn", "fox-news", "bloomberg")
    ).cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            appEntryUseCase.getOnboardingStatus().collect {
                _isOnboardingCompleted.value = it
                delay(500)
                _splashScreenCondition.value = false
            }
        }

        viewModelScope.launch {
            newsUseCase.getSources().collect {
                _newsSources.value = it
            }
        }
    }

}