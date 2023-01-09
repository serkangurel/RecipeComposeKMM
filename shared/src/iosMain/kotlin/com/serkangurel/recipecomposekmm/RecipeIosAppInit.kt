package com.serkangurel.recipecomposekmm

import com.serkangurel.recipecomposekmm.di.driverFactoryModule

fun recipeIosAppInit() {
    recipeAppInit {
        modules(driverFactoryModule)
    }
}