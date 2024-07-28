package com.ali.advancedtask.data

import com.ali.advancedtask.R

val categories = listOf(
    Categoty("Coffee",R.drawable.coffee),
    Categoty("Resturants", R.drawable.resturant),
    Categoty("Supermarket", R.drawable.coke),
    Categoty("House made", R.drawable.bakeries),
)

data class Categoty (val catName: String, val catImage: Int)