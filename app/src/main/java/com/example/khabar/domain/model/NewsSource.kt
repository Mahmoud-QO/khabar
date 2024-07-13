package com.example.khabar.domain.model

data class NewsSource(
    val id: String,
    val name: String,
    val category: String? = null,
    val country: String? = null,
    val description: String? = null,
    val language: String? = null,
    val url: String? = null
)