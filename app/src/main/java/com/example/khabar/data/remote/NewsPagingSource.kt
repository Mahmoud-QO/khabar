package com.example.khabar.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.khabar.domain.model.Article

class NewsPagingSource(
    private val newsApiService: NewsApiService,
    private val endpoint: NewsEndpoint
) : PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 1
            val loadSize = params.loadSize

            val response = when (endpoint) {
                is NewsEndpoint.TopHeadlines ->
                    newsApiService.getTopHeadlines(endpoint.country, page, loadSize)
                is NewsEndpoint.Everything ->
                    newsApiService.getNews(endpoint.sources, page, loadSize)
                is NewsEndpoint.Search ->
                    newsApiService.searchNews(endpoint.query, endpoint.sources, page, loadSize)
            }

            LoadResult.Page(
                data = response.articles, // TODO[M]: consider using '.distinctBy { it.title }' to remove duplicates
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.articles.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        // Not required for basic implementation, but you can handle refresh logic here
        return null

        // TODO[M]: consider trying this:
        /*
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
         */
    }
}