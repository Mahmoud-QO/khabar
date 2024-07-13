package com.example.khabar.domain.usecase

import com.example.khabar.domain.repository.NewsRepository
import javax.inject.Inject

data class NewsUseCase(
    val getTopHeadlines: GetTopHeadlinesUseCase,
    val searchNews: SearchNewsUseCase,
    val getSources: GetSourcesUseCase
)

class GetTopHeadlinesUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    operator fun invoke(
        sources: List<String>, country: String? = null, category: String? = null
    ) = newsRepository.getTopHeadlines(sources, country, category)
}

class SearchNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    operator fun invoke(
        sources: List<String>, query: String? = null, sortBy: String? = null
    ) = newsRepository.searchNews(sources, query, sortBy)
}

class GetSourcesUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    operator fun invoke(
        country: String? = null, category: String? = null, language: String? = null
    ) = newsRepository.getSources(country, category, language)
}