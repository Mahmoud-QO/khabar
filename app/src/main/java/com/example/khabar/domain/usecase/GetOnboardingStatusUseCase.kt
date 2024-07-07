package com.example.khabar.domain.usecase

import com.example.khabar.domain.repository.UserRepository

class GetOnboardingStatusUseCase(private val userRepository: UserRepository)
{
    operator fun invoke() = userRepository.isOnboardingCompleted()

}
