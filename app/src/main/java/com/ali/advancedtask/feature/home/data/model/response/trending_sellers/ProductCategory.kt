package com.ali.advancedtask.feature.home.data.model.response.trending_sellers


import com.google.gson.annotations.SerializedName

data class ProductCategory(
    @SerializedName("active")
    val active: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("name_ar")
    val nameAr: String,
    @SerializedName("name_en")
    val nameEn: String
)