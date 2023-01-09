package com.serkangurel.recipecomposekmm

import com.serkangurel.recipecomposekmm.data.di.driverFactoryModule
import org.koin.dsl.KoinAppDeclaration

fun recipeAndroidAppInit(appDeclaration: KoinAppDeclaration = {}) {
    recipeAppInit {
        appDeclaration()
        modules(driverFactoryModule)
    }
}