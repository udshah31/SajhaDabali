package com.example.sajhadabali.api

import com.example.sajhadabali.model.category.Category
import com.example.sajhadabali.model.news.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    //All Categories
    @GET("categories")
    suspend fun getCategories(
        @Query("per_page") per_page: Int,
        @Query("exclude") id: Int
    ): Response<List<Category>>

    //Top Page News
    @GET("posts")
    suspend fun getPosts(
        @Query("per_page") per_page: Int,
        @Query("orderby") date: String,
        @Query("page") page: Int
    ): Response<List<News>>

    //categoryListItem
    @GET("posts")
    suspend fun getCategoryListItem(
        @Query("categories") catId: Int,
        @Query("page") page: Int
    ): Response<List<News>>

    //search
    @GET("posts")
    suspend fun getSearchItem(
        @Query("") query: String,
        @Query("page") page: Int
    ): Response<List<News>>

}