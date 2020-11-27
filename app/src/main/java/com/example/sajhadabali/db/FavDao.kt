package com.example.sajhadabali.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.sajhadabali.model.news.News

@Dao
interface FavDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(news: News): Long

    @Query("SELECT * FROM news")
    fun getAllNews(): LiveData<List<News>>

    @Delete
    suspend fun deleteNews(news: News)

}