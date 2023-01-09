package com.serkangurel.recipecomposekmm.data.remote.service

import com.serkangurel.recipecomposekmm.data.model.domain.Recipe
import com.serkangurel.recipecomposekmm.data.model.response.RecipeResponseModel
import com.serkangurel.recipecomposekmm.data.model.response.RecipeSearchResponseModel
import com.serkangurel.recipecomposekmm.util.toRecipe
import com.serkangurel.recipecomposekmm.util.toRecipeList
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class RecipeServiceImpl(
    private val httpClient: HttpClient
) : RecipeService {

    override suspend fun search(page: Int, query: String): List<Recipe> {
        return httpClient.get {
            url {
                path("/api/recipe/search")
                parameters.append("page", "$page")
                parameters.append("query", query)
            }
        }.body<RecipeSearchResponseModel>().recipes?.toRecipeList() ?: emptyList()
    }

    override suspend fun get(id: Int): Recipe {

        return httpClient.get {
            url {
                path("/api/recipe/get?id=$id")
            }
        }.body<RecipeResponseModel>().toRecipe()
    }
}