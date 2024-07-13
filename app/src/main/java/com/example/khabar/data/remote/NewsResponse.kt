package com.example.khabar.data.remote

import com.example.khabar.domain.model.Article
import com.example.khabar.domain.model.NewsSource

data class NewsArticlesResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)


data class NewsSourcesResponse(
    val sources: List<NewsSource>,
    val status: String
)