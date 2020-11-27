package com.example.sajhadabali.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sajhadabali.model.category.Category
import com.example.sajhadabali.model.news.News
import com.example.sajhadabali.repository.NewsRepository
import com.example.sajhadabali.utils.Constants.Companion.ORDER_BY_DATE
import com.example.sajhadabali.utils.Constants.Companion.PER_PAGE
import com.example.sajhadabali.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

@Suppress("DEPRECATION")
class NewsViewModel(
    val app: Application,
    private val repository: NewsRepository
) : AndroidViewModel(app) {

    val topNews: MutableLiveData<Resource<List<News>>> = MutableLiveData()
    val categoryNews: MutableLiveData<Resource<List<Category>>> = MutableLiveData()
    var categoryListItem: MutableLiveData<Resource<List<News>>> = MutableLiveData()

    var currentPage = 1
    var nextPage = currentPage + 1
    var category = 100


    fun getTopNews() = viewModelScope.launch {
        topNews.postValue(Resource.Loading())

        try {

            if (hasNetwork(app) == true) {
                val response = repository.topNews(PER_PAGE, ORDER_BY_DATE, currentPage)
                topNews.postValue(handelTopNews(response))
            } else {
                topNews.postValue(Resource.Error("No internet connection"))
            }

        } catch (t: Throwable) {
            when (t) {
                is IOException -> categoryNews.postValue(Resource.Error("Network Failure"))
                else -> categoryNews.postValue(Resource.Error("Conversion Error"))
            }
        }

    }

    fun getNextTopNews() = viewModelScope.launch {
        topNews.postValue(Resource.Loading())

        try {

            if (hasNetwork(app) == true) {
                val response = repository.topNews(PER_PAGE, ORDER_BY_DATE, nextPage)
                topNews.postValue(handelTopNews(response))
            } else {
                topNews.postValue(Resource.Error("No internet connection"))
            }

        } catch (t: Throwable) {
            when (t) {
                is IOException -> categoryNews.postValue(Resource.Error("Network Failure"))
                else -> categoryNews.postValue(Resource.Error("Conversion Error"))
            }
        }


    }


    private fun handelTopNews(response: Response<List<News>>): Resource<List<News>> {
        if (response.isSuccessful) {
            response.body()?.let { responseResult ->

                return Resource.Success(responseResult)
            }
        }

        return Resource.Error(response.message())
    }


    fun getCategory() = viewModelScope.launch {
        categoryNews.postValue(Resource.Loading())

        try {
            if (hasNetwork(app) == true) {
                val response = repository.categories(category, currentPage)
                categoryNews.postValue(handleCategories(response))
            } else {
                categoryNews.postValue(Resource.Error("No internet connection"))
            }


        } catch (t: Throwable) {
            when (t) {
                is IOException -> categoryNews.postValue(Resource.Error("Network Failure"))
                else -> categoryNews.postValue(Resource.Error("Conversion Error"))
            }
        }

    }


    private fun handleCategories(response: Response<List<Category>>): Resource<List<Category>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->

                return Resource.Success(resultResponse)

            }
        }

        return Resource.Error(response.message())
    }


    fun getCategoryListItem(catId: Int) = viewModelScope.launch {
        categoryListItem.postValue(Resource.Loading())

        try {
            if (hasNetwork(app) == true) {
                val response = repository.categoryPosts(catId, currentPage)
                categoryListItem.postValue(handelCategoriesItemList(response))
            } else {
                categoryListItem.postValue(Resource.Error("No internet connection"))
            }


        } catch (t: Throwable) {
            when (t) {
                is IOException -> categoryListItem.postValue(Resource.Error("Network Failure"))
                else -> categoryListItem.postValue(Resource.Error("Conversion Error"))
            }
        }


    }


    fun getCategoryListItemNext(catId: Int) = viewModelScope.launch {
        categoryListItem.postValue(Resource.Loading())

        try {
            if (hasNetwork(app) == true) {
                val response = repository.categoryPosts(catId, nextPage)
                categoryListItem.postValue(handelCategoriesItemList(response))
            } else {
                categoryListItem.postValue(Resource.Error("No internet connection"))
            }


        } catch (t: Throwable) {
            when (t) {
                is IOException -> categoryListItem.postValue(Resource.Error("Network Failure"))
                else -> categoryListItem.postValue(Resource.Error("Conversion Error"))
            }
        }

    }


    private fun handelCategoriesItemList(response: Response<List<News>>): Resource<List<News>> {
        if (response.isSuccessful) {
            response.body()?.let { responseResult ->
                return Resource.Success(responseResult)
            }
        }
        return Resource.Error(response.message())
    }


    fun saveNews(news: News) = viewModelScope.launch {
        repository.insert(news)
    }


    fun getSavedNews() = repository.getSavedNews()

    fun deleteNew(news: News) = viewModelScope.launch {
        repository.deleteNews(news)
    }


    private fun hasNetwork(context: Context): Boolean? {
        var isConnected: Boolean? = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true

        return isConnected
    }


}