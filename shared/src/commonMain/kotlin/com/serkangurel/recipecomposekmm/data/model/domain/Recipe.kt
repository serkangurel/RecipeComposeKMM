package com.serkangurel.recipecomposekmm.data.model.domain

import kotlinx.datetime.LocalDateTime

data class Recipe (
    val id: Int,
    val title: String,
    val publisher: String,
    val featuredImage: String,
    val rating: Int,
    val sourceUrl: String,
    val ingredients: List<String> = listOf(),
    val dateAdded: LocalDateTime,
    val dateUpdated: LocalDateTime,
)