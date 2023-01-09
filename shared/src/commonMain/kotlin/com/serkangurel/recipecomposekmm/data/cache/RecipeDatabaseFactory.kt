package com.serkangurel.recipecomposekmm.data.cache

import com.serkangurel.recipecomposekmm.data.model.domain.Recipe
import com.serkangurel.recipecomposekmm.util.DatetimeUtil

class RecipeDatabaseFactory(
    private val driverFactory: DriverFactory
) {
    fun createDatabase(): RecipeDatabase {
        return RecipeDatabase(driverFactory.createDriver())
    }
}

fun Recipe_Entity.toRecipe(): Recipe {
    val datetimeUtil = DatetimeUtil()
    return Recipe(
        id = id.toInt(),
        title = title,
        publisher = publisher,
        featuredImage = featured_image,
        rating = rating.toInt(),
        sourceUrl = source_url,
        ingredients = ingredients.convertIngredientsToList(),
        dateAdded = datetimeUtil.toLocalDate(date_added.toLong()),
        dateUpdated = datetimeUtil.toLocalDate(date_updated.toLong()),
    )
}

fun List<Recipe_Entity>.toRecipeList(): List<Recipe> {
    return map { it.toRecipe() }
}

/**
 * "Carrot, potato, Chicken, ..."
 */
fun List<String>.convertIngredientListToString(): String {
    val ingredientsString = StringBuilder()
    for (ingredient in this) {
        ingredientsString.append("$ingredient,")
    }
    return ingredientsString.toString()
}

fun String.convertIngredientsToList(): List<String> {
    val list: ArrayList<String> = ArrayList()
    for (ingredient in split(",")) {
        list.add(ingredient)
    }
    return list
}













