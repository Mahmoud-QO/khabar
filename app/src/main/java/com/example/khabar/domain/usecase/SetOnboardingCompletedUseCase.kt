package com.example.khabar.domain.usecase

import com.example.khabar.domain.repository.UserRepository

class SetOnboardingCompletedUseCase(private val userRepository: UserRepository)
{
    suspend operator fun invoke() = userRepository.setOnboardingCompleted()

}