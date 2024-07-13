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

interface NewsApiService
{
    @GET("everything")
    suspend fun getEverything(
        @Query("sources") sources: String,
        @Query("q") searchQuery: String? = null,
        @Query("sortBy") sortBy: String? = null,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String = NEWS_API_KEY
    ): NewsArticlesResponse

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("sources") sources: String,
        @Query("country") country: String? = null,
        @Query("category") category: String? = null,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String = NEWS_API_KEY
    ): NewsArticlesResponse

    @GET("top-headlines/sources")
    suspend fun getSources(
        @Query("country") country: String? = null,
        @Query("category") category: String? = null,
        @Query("language") language: String? = null,
        @Query("apiKey") apiKey: String = NEWS_API_KEY
    ): NewsSourcesResponse
}