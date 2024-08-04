package com.ali.advancedtask.feature.home.domin.repository

import com.ali.advancedtask.feature.home.data.model.response.Category

interface CategoriesRepository {

    suspend fun getAllCategories(): List<Category>
}