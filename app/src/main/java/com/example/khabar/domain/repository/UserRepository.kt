package com.example.khabar.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserRepository
{
    fun isOnboardingCompleted(): Flow<Boolean>
    suspend fun setOnboardingCompleted()

}