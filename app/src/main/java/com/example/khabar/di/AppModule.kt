package com.example.khabar.di

import android.app.Application
import android.content.Context
import com.example.khabar.data.datastore.PreferencesDataStore
import com.example.khabar.data.repository.UserRepositoryImpl
import com.example.khabar.domain.repository.UserRepository
import com.example.khabar.domain.usecase.AppEntryUseCase
import com.example.khabar.domain.usecase.GetOnboardingStatusUseCase
import com.example.khabar.domain.usecase.CompleteOnboardingUseCase
import com.example.khabar.domain.usecase.OnboardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppEntryUseCase(userRepository: UserRepository): AppEntryUseCase =
        AppEntryUseCase(getOnboardingStatus = GetOnboardingStatusUseCase(userRepository))

    @Provides
    @Singleton
    fun provideOnboardingUseCase(userRepository: UserRepository): OnboardingUseCase =
        OnboardingUseCase(completeOnboarding = CompleteOnboardingUseCase(userRepository))

    @Provides
    @Singleton
    fun provideUserRepository(preferencesDataStore: PreferencesDataStore): UserRepository =
        UserRepositoryImpl(preferencesDataStore)

    @Provides
    @Singleton
    fun providePreferencesDataStore(application: Application): PreferencesDataStore =
        PreferencesDataStore(application)
}