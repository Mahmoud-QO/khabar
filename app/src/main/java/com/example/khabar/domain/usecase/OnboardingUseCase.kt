package com.example.khabar.domain.usecase

import com.example.khabar.data.datastore.PreferencesDataStore
import com.example.khabar.data.repository.UserRepositoryImpl
import com.example.khabar.domain.repository.UserRepository
import javax.inject.Inject

data class OnboardingUseCase(
    val completeOnboarding: CompleteOnboardingUseCase
)

class GetOnboardingStatusUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke() = userRepository.isOnboardingCompleted()
}

class CompleteOnboardingUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() = userRepository.setOnboardingCompleted()
}
