package com.serkangurel.recipecomposekmm.data.cache

import com.squareup.sqldelight.db.SqlDriver

expect class DriverFactory {
    fun createDriver(): SqlDriver
}