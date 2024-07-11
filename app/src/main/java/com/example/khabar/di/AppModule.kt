package com.example.khabar.di

import android.app.Application
import com.example.khabar.data.datastore.PreferencesDataStore
import com.example.khabar.data.remote.NewsApi
import com.example.khabar.data.remote.NewsApiService
import com.example.khabar.data.repository.NewsRepositoryImpl
import com.example.khabar.data.repository.UserRepositoryImpl
import com.example.khabar.domain.repository.NewsRepository
import com.example.khabar.domain.repository.UserRepository
import com.example.khabar.domain.usecase.AppEntryUseCase
import com.example.khabar.domain.usecase.GetOnboardingStatusUseCase
import com.example.khabar.domain.usecase.CompleteOnboardingUseCase
import com.example.khabar.domain.usecase.GetNewsUseCase
import com.example.khabar.domain.usecase.GetTopHeadlinesUseCase
import com.example.khabar.domain.usecase.NewsUseCase
import com.example.khabar.domain.usecase.OnboardingUseCase
import com.example.khabar.domain.usecase.SearchNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule
{
    // UseCases ////////////////////////////////////////////////////////////////////////////////////

    @Provides
    @Singleton
    fun provideNewsUseCase(newsRepository: NewsRepository): NewsUseCase =
        NewsUseCase(
            getTopHeadlines = GetTopHeadlinesUseCase(newsRepository),
            getNews = GetNewsUseCase(newsRepository),
            searchNews = SearchNewsUseCase(newsRepository)
        )

    @Provides
    @Singleton
    fun provideAppEntryUseCase(userRepository: UserRepository): AppEntryUseCase =
        AppEntryUseCase(getOnboardingStatus = GetOnboardingStatusUseCase(userRepository))

    @Provides
    @Singleton
    fun provideOnboardingUseCase(userRepository: UserRepository): OnboardingUseCase =
        OnboardingUseCase(completeOnboarding = CompleteOnboardingUseCase(userRepository))


    // Repositories ////////////////////////////////////////////////////////////////////////////////

    @Provides
    @Singleton
    fun provideNewsRepository(newsApiService: NewsApiService): NewsRepository =
        NewsRepositoryImpl(newsApiService)

    @Provides
    @Singleton
    fun provideUserRepository(preferencesDataStore: PreferencesDataStore): UserRepository =
        UserRepositoryImpl(preferencesDataStore)


    // DataSources /////////////////////////////////////////////////////////////////////////////////

    @Provides
    @Singleton
    fun provideNewsApiService(): NewsApiService = NewsApi.newsApiService

    @Provides
    @Singleton
    fun providePreferencesDataStore(application: Application): PreferencesDataStore =
        PreferencesDataStore(application)
}