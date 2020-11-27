package com.example.sajhadabali.model.news

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Entity(tableName = "news")
@Parcelize
data class News(

    @Expose
    @SerializedName("excerpt")
    var excerpt: @RawValue Excerpt,

    @Expose
    @SerializedName("content")
    var content: @RawValue Content,

    @Expose
    @SerializedName("title")
    var title: @RawValue Title,

    @Expose
    @SerializedName("slug")
    var slug: String?,

    @Expose
    @SerializedName("modified_gmt")
    var modified_gmt: String?,

    @Expose
    @SerializedName("modified")
    var modified: String?,

    @Expose
    @SerializedName("date_gmt")
    var date_gmt: String?,

    @Expose
    @SerializedName("date")
    var date: String?,

    @PrimaryKey(autoGenerate = true)
    @Expose
    @SerializedName("id")
    var id: Int? = null,

    @Expose
    @SerializedName("jetpack_featured_media_url")
    var image: String?
) : Parcelable

