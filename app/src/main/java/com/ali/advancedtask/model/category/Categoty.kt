package com.ali.advancedtask.model.category

import com.ali.advancedtask.R

val categories = listOf(
    Categoty("Coffee",R.drawable.coffee),
    Categoty("Resturants", R.drawable.resturant),
    Categoty("Supermarket", R.drawable.coke),
    Categoty("House made", R.drawable.bakeries),
)

data class Categoty (val catName: String, val catImage: Int)