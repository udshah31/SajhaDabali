package com.example.sajhadabali.repository

import com.example.sajhadabali.api.ServiceGenerator
import com.example.sajhadabali.db.AppDatabase
import com.example.sajhadabali.model.news.News

class NewsRepository(
    val db: AppDatabase
) {

    suspend fun topNews(perPage: Int, order: String, currentPage: Int) =
        ServiceGenerator.api.getPosts(perPage, order, currentPage)

    suspend fun categories(perPage: Int, id: Int) =
        ServiceGenerator.api.getCategories(perPage, id)

    suspend fun categoryPosts(categoryId: Int, currentPage: Int) =
        ServiceGenerator.api.getCategoryListItem(categoryId, currentPage)

    suspend fun insert(news: News) = db.getNewsDao().insert(news)

    fun getSavedNews() = db.getNewsDao().getAllNews()

    suspend fun deleteNews(news: News) = db.getNewsDao().deleteNews(news)
}