package com.serkangurel.recipecomposekmm.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeSearchResponseModel(
    @SerialName("count")
    var count: Int? = null,

    @SerialName("results")
    var recipes: List<RecipeResponseModel>? = null,
)