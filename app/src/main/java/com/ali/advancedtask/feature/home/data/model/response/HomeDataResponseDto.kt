package com.ali.advancedtask.feature.home.data.model.response

import com.ali.advancedtask.feature.home.data.model.response.base_categories.BaseCategoriesResponseDto
import com.ali.advancedtask.feature.home.data.model.response.popular_sellers.PopularSellersResponseDto
import com.ali.advancedtask.feature.home.data.model.response.trending_sellers.TrendingSellersResponseDto

data class HomeDataResponseDto(
    val baseCategoriesResponseDto: BaseCategoriesResponseDto,
    val trendingSellersResponseDto: TrendingSellersResponseDto,
    val popularSellersResponseDto: PopularSellersResponseDto
)
