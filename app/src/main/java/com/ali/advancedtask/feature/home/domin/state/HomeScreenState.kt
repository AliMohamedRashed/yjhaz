package com.ali.advancedtask.feature.home.domin.state

import com.ali.advancedtask.feature.home.data.model.response.base_categories.BaseCategoriesDataResponse
import com.ali.advancedtask.feature.home.data.model.response.popular_sellers.PopularSellersDataResponse
import com.ali.advancedtask.feature.home.data.model.response.trending_sellers.TrendingSellersDataResponse


data class HomeScreenState (
    val trendingSellers: List<TrendingSellersDataResponse>,
    val popularSellers: List<PopularSellersDataResponse>,
    val baseCategories: List<BaseCategoriesDataResponse>,
    val success: Boolean,
    val isLoading: Boolean,
    val error: String? = null
)