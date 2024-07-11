package com.example.khabar.domain.usecase

import com.example.khabar.domain.repository.NewsRepository
import javax.inject.Inject

data class NewsUseCase(
    val getTopHeadlines: GetTopHeadlinesUseCase,
    val getNews: GetNewsUseCase,
    val searchNews: SearchNewsUseCase
)

class GetTopHeadlinesUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    operator fun invoke(country: String) = newsRepository.getTopHeadlines(country)
}

class GetNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    operator fun invoke(sources: List<String>) = newsRepository.getNews(sources)
}

class SearchNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    operator fun invoke(query: String, sources: List<String>) =
        newsRepository.searchNews(query, sources)
}