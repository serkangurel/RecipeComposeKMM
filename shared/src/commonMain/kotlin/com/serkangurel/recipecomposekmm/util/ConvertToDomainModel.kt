package com.serkangurel.recipecomposekmm.util

import com.serkangurel.recipecomposekmm.data.model.domain.Recipe
import com.serkangurel.recipecomposekmm.data.model.response.RecipeResponseModel

fun RecipeResponseModel.toRecipe(): Recipe {
    val datetimeUtil = DatetimeUtil()
    return Recipe(
        id = pk ?: 0,
        title = title ?: "",
        featuredImage = featuredImage ?: "",
        rating = rating ?: 0,
        publisher = publisher ?: "",
        sourceUrl = sourceUrl ?: "",
        ingredients = ingredients ?: emptyList(),
        dateAdded = datetimeUtil.toLocalDate(longDateAdded ?: 0L),
        dateUpdated = datetimeUtil.toLocalDate(longDateUpdated ?: 0L),
    )
}

fun List<RecipeResponseModel>.toRecipeList(): List<Recipe> {
    return map { it.toRecipe() }
}