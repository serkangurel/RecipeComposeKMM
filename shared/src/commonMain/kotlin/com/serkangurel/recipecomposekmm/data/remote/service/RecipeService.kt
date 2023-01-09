package com.serkangurel.recipecomposekmm.data.remote.service

import com.serkangurel.recipecomposekmm.data.model.domain.Recipe

interface RecipeService {

    suspend fun search(
        page: Int,
        query: String,
    ): List<Recipe>

    suspend fun get(
        id: Int
    ): Recipe
}