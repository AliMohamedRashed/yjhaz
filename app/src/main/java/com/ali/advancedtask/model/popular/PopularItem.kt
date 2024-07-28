package com.ali.advancedtask.model.popular


import com.ali.advancedtask.R

val popularItems = listOf(
    PopularItem("Bazooka",0.0,"15 Km",false, R.drawable.bazooka),
    PopularItem("Wings",0.0,"15 Km",false, R.drawable.wings),
    PopularItem("Papa jhons",0.0,"15 Km",false, R.drawable.papa_jhons)
)
data class PopularItem (val popularName: String, val popularRate: Double, val popularDistance: String, val popularIsFavourite: Boolean, val popularImage: Int)