package com.example.khabar.domain.repository

import androidx.paging.PagingData
import com.example.khabar.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository
{
    fun getTopHeadlines(country: String): Flow<PagingData<Article>>
    fun getNews(sources: List<String>): Flow<PagingData<Article>>
    fun searchNews(query: String, sources: List<String>): Flow<PagingData<Article>>
}