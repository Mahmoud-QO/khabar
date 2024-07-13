package com.example.khabar.domain.repository

import androidx.paging.PagingData
import com.example.khabar.domain.model.Article
import com.example.khabar.domain.model.NewsSource
import kotlinx.coroutines.flow.Flow

interface NewsRepository
{
    fun getTopHeadlines(
        sources: List<String>, country: String? = null, category: String? = null
    ): Flow<PagingData<Article>>

    fun searchNews(
        sources: List<String>, query: String? = null, sortBy: String? = null
    ): Flow<PagingData<Article>>

    fun getSources(
        country: String? = null, category: String? = null, language: String? = null
    ): Flow<List<NewsSource>>
}