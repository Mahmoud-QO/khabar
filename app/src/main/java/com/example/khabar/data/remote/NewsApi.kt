package com.example.khabar.data.remote

import com.example.khabar.util.NEWS_API_KEY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://newsapi.org/v2/"

object NewsApi
{
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val newsApiService : NewsApiService by lazy { retrofit.create(NewsApiService::class.java) }
}

sealed class NewsEndpoint {
    data class TopHeadlines(val country: String) : NewsEndpoint()
    data class Everything(val sources: String) : NewsEndpoint()
    data class Search(val query: String, val sources: String) : NewsEndpoint()
}

interface NewsApiService
{
    @GET("everything")
    suspend fun getNews(
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String = NEWS_API_KEY
    ): NewsResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String = NEWS_API_KEY
    ): NewsResponse

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String = NEWS_API_KEY
    ): NewsResponse

}