package com.ali.advancedtask.feature.home.data.model.request

import retrofit2.http.Query

data class PopularSellersRequestDto(
    val lat: Double,
    val lng: Double,
    val filter: Int
)