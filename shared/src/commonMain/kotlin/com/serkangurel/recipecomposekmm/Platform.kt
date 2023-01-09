package com.serkangurel.recipecomposekmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform