package com.ali.advancedtask.feature.home.data.model.response.popular_sellers


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("active")
    val active: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String
)