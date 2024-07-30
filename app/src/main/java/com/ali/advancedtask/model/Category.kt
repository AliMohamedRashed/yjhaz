package com.ali.advancedtask.model

import com.google.gson.annotations.SerializedName
data class Category (
    @SerializedName("name")
    val catName: String,
    @SerializedName("imageUrl")
    val catImage: String
)