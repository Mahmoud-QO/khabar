package com.example.khabar.domain.model

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: NewsSource,
    val title: String,
    val url: String,
    val urlToImage: String
)