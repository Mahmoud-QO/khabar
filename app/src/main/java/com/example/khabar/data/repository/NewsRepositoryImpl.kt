package com.example.khabar.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.khabar.data.remote.NewsApiService
import com.example.khabar.data.remote.NewsPagingSource
import com.example.khabar.data.remote.PageableNewsEndpoint
import com.example.khabar.domain.model.Article
import com.example.khabar.domain.model.NewsSource
import com.example.khabar.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApiService: NewsApiService
): NewsRepository {

    override fun getTopHeadlines(
        sources: List<String>, country: String?, category: String?
    ): Flow<PagingData<Article>> = paginate(
        PageableNewsEndpoint.TopHeadlines(sources.joinToString(","), country, category)
    )

    override fun searchNews(
        sources: List<String>, query: String?, sortBy: String?
    ): Flow<PagingData<Article>> = paginate(
        PageableNewsEndpoint.Everything(sources.joinToString(","), query, sortBy)
    )

    override fun getSources(
        country: String?, category: String?, language: String?
    ): Flow<List<NewsSource>> = flow {
        emit(newsApiService.getSources(country, category, language).sources)
    }

    private fun paginate(endpoint: PageableNewsEndpoint): Flow<PagingData<Article>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { NewsPagingSource(newsApiService, endpoint) }
    ).flow
}