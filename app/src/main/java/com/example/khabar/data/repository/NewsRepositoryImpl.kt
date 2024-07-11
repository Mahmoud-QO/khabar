package com.example.khabar.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.khabar.data.remote.NewsApiService
import com.example.khabar.data.remote.NewsEndpoint
import com.example.khabar.data.remote.NewsPagingSource
import com.example.khabar.domain.model.Article
import com.example.khabar.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApiService: NewsApiService
): NewsRepository {
    override fun getTopHeadlines(country: String) =
        paginate(NewsEndpoint.TopHeadlines(country))

    override fun getNews(sources: List<String>) =
        paginate(NewsEndpoint.TopHeadlines(sources.joinToString(",")))

    override fun searchNews(query: String, sources: List<String>) =
        paginate(NewsEndpoint.Search(query, sources.joinToString(",")))

    private fun paginate(endpoint: NewsEndpoint): Flow<PagingData<Article>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { NewsPagingSource(newsApiService, endpoint) }
    ).flow
}