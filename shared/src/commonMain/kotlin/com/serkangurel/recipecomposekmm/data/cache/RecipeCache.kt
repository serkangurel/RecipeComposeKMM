package com.serkangurel.recipecomposekmm.data.cache

import com.serkangurel.recipecomposekmm.data.model.domain.Recipe

interface RecipeCache {

    fun insert(recipe: Recipe)

    fun insert(recipes: List<Recipe>)

    fun search(query: String, page: Int): List<Recipe>

    fun getAll(page: Int): List<Recipe>

    fun get(recipeId: Int): Recipe
}