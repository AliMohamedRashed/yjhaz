package com.ali.advancedtask.feature.home.data.model.response

import com.google.gson.annotations.SerializedName

data class PopularItem (
    @SerializedName("name")
    val popularName: String,
    @SerializedName("rate")
    val popularRate: Double,
    @SerializedName("distance")
    val popularDistance: String,
    @SerializedName("is_favourite")
    val popularIsFavourite: Boolean,
    @SerializedName("image")
    val popularImage: String
)