package com.ali.advancedtask.model.category

import com.ali.advancedtask.R

val categories = listOf(
    Category("Coffee",R.drawable.coffee),
    Category("Restaurants", R.drawable.ramen),
    Category("Supermarket", R.drawable.resturant),
    Category("House made", R.drawable.bakeries),
)

data class Category (val catName: String, val catImage: Int)