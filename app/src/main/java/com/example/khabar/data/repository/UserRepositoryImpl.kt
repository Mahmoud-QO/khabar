package com.example.khabar.data.repository

import com.example.khabar.data.datastore.PreferencesDataStore
import com.example.khabar.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(private val preferencesDataStore: PreferencesDataStore): UserRepository
{
    override fun isOnboardingCompleted(): Flow<Boolean> =
        preferencesDataStore.getOnboardingCompleted()

    override suspend fun setOnboardingCompleted() =
        preferencesDataStore.setOnboardingCompleted(true)
}