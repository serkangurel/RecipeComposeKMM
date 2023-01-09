@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.serkangurel.recipecomposekmm.data.remote.repository

import com.serkangurel.recipecomposekmm.data.cache.RecipeCache
import com.serkangurel.recipecomposekmm.data.model.domain.Recipe
import com.serkangurel.recipecomposekmm.data.remote.service.RecipeService

class RecipeRepository(
    private val recipeService: RecipeService,
    private val recipeCache: RecipeCache
) {
    suspend fun searchRecipes(
        page: Int,
        query: String,
        onSuccess: (List<Recipe>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        runCatching {
            // query the cache
            val cacheResult = if (query.isBlank()) {
                recipeCache.getAll(page = page)
            } else {
                recipeCache.search(
                    query = query,
                    page = page,
                )
            }
            cacheResult.ifEmpty {
                recipeService.search(page, query).also {
                    recipeCache.insert(it)
                }
            }
        }.onSuccess {
            onSuccess.invoke(it)
        }.onFailure {
            onFailure.invoke(it)
        }
    }

    fun getRecipe(
        recipeId: Int,
        onSuccess: (Recipe) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        runCatching {
            recipeCache.get(recipeId)
        }.onSuccess {
            onSuccess(it)
        }.onFailure {
            onFailure(it)
        }
    }
}