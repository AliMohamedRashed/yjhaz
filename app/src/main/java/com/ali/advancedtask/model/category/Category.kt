package com.ali.advancedtask.model.category

import com.google.gson.annotations.SerializedName

//val categories = listOf(
//    Category("Coffee",R.drawable.coffee),
//    Category("Restaurants", R.drawable.ramen),
//    Category("Supermarket", R.drawable.resturant),
//    Category("House made", R.drawable.bakeries),
//)

data class Category (
    @SerializedName("name")
    val catName: String,
    @SerializedName("imageUrl")
    val catImage: String
)