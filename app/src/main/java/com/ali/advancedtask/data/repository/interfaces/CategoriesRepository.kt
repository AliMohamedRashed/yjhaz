package com.ali.advancedtask.data.repository.interfaces

import com.ali.advancedtask.domain.model.Category

interface CategoriesRepository {

    suspend fun getAllCategories(): List<Category>
}