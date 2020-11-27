package com.example.sajhadabali.ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sajhadabali.repository.NewsRepository

@Suppress("UNCHECKED_CAST")
class NewsViewModelFactory(
    val app: Application,
    private val repository: NewsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(app, repository) as T
    }


}