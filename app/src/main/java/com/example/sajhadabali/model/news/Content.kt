package com.example.sajhadabali.model.news

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Content(
    @Expose
    @SerializedName("rendered")
    val rendered: String
)