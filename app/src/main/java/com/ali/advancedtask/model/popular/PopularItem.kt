package com.ali.advancedtask.model.popular

import com.google.gson.annotations.SerializedName


//val popularItems = listOf(
//    PopularItem("Bazooka",0.0,"15 Km",false, R.drawable.bazooka),
//    PopularItem("Wings",0.0,"15 Km",false, R.drawable.wings),
//    PopularItem("Papa jhons",0.0,"15 Km",false, R.drawable.papa_jhons)
//)
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