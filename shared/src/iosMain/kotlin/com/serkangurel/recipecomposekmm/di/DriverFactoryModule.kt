package com.serkangurel.recipecomposekmm.di

import com.serkangurel.recipecomposekmm.data.cache.DriverFactory
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val driverFactoryModule = module {
    singleOf(::DriverFactory)
}