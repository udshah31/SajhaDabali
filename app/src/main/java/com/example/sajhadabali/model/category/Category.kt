package com.example.sajhadabali.model.category

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(

    @Expose
    @SerializedName("id")
    var id: Int,

    @Expose
    @SerializedName("count")
    var count: Int,

    @Expose
    @SerializedName("description")
    var description: String,

    @Expose
    @SerializedName("link")
    var link: String,

    @Expose
    @SerializedName("name")
    var name: String,

    @Expose
    @SerializedName("slug")
    var slug: String,

    @Expose
    @SerializedName("taxonomy")
    var taxonomy: String,

    @Expose
    @SerializedName("parent")
    var parent: Int
) : Parcelable




