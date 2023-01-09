package com.serkangurel.recipecomposekmm.data.cache

import com.serkangurel.recipecomposekmm.data.model.domain.Recipe
import com.serkangurel.recipecomposekmm.util.Constants
import com.serkangurel.recipecomposekmm.util.DatetimeUtil

class RecipeCacheImpl(
    private val recipeDatabase: RecipeDatabase,
    private val datetimeUtil: DatetimeUtil,
) : RecipeCache {

    private var queries: RecipeDbQueries = recipeDatabase.recipeDbQueries

    override fun insert(recipe: Recipe) {
        queries.insertRecipe(
            id = recipe.id.toLong(),
            title = recipe.title,
            publisher = recipe.publisher,
            featured_image = recipe.featuredImage,
            rating = recipe.rating.toLong(),
            source_url = recipe.sourceUrl,
            ingredients = recipe.ingredients.convertIngredientListToString(),
            date_updated = datetimeUtil.toEpochMilliseconds(recipe.dateUpdated).toDouble(),
            date_added = datetimeUtil.toEpochMilliseconds(recipe.dateAdded).toDouble(),
        )
    }

    override fun insert(recipes: List<Recipe>) {
        for (recipe in recipes) {
            insert(recipe)
        }
    }

    override fun search(query: String, page: Int): List<Recipe> {
        return queries.searchRecipes(
            query = query,
            pageSize = Constants.PAGE_SIZE.toLong(),
            offset = ((page - 1) * Constants.PAGE_SIZE).toLong()
        ).executeAsList().toRecipeList()
    }

    override fun getAll(page: Int): List<Recipe> {
        return queries.getAllRecipes(
            pageSize = Constants.PAGE_SIZE.toLong(),
            offset = ((page - 1) * Constants.PAGE_SIZE).toLong()
        ).executeAsList().toRecipeList()
    }

    override fun get(recipeId: Int): Recipe {
        return queries.getRecipeById(id = recipeId.toLong()).executeAsOne().toRecipe()
    }
}



