package com.example.sajhadabali.db

import androidx.room.TypeConverter
import com.example.sajhadabali.model.news.Content
import com.example.sajhadabali.model.news.Excerpt
import com.example.sajhadabali.model.news.Title

class Converters {

    @TypeConverter
    fun fromTitle(title: Title): String {
        return title.rendered
    }

    @TypeConverter
    fun toTitle(rendered: String): Title {
        return Title(rendered)
    }


    @TypeConverter
    fun fromContent(content: Content): String {
        return content.rendered
    }

    @TypeConverter
    fun toContent(rendered: String): Content {
        return Content(rendered)
    }


    @TypeConverter
    fun fromExcerpt(excerpt: Excerpt): String {
        return excerpt.rendered
    }

    @TypeConverter
    fun toExcerpt(rendered: String): Excerpt {
        return Excerpt(rendered)
    }
}