package com.ali.advancedtask.feature.home.data.model.response.popular_sellers


import com.google.gson.annotations.SerializedName

data class PopularSellersDataResponse(
    @SerializedName("address")
    val address: String,
    @SerializedName("appointments")
    val appointments: String,
    @SerializedName("categories")
    val categories: List<Category>,
    @SerializedName("description")
    val description: String,
    @SerializedName("distance")
    val distance: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("information")
    val information: Information,
    @SerializedName("is_favorite")
    val isFavorite: Boolean,
    @SerializedName("lat")
    val lat: String,
    @SerializedName("lng")
    val lng: String,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("popular")
    val popular: Int,
    @SerializedName("product_categories")
    val productCategories: List<ProductCategory>,
    @SerializedName("rate")
    val rate: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("token")
    val token: String,
    @SerializedName("trending")
    val trending: Int,
    @SerializedName("type")
    val type: Int
)