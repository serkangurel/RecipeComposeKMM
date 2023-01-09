package com.serkangurel.recipecomposekmm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.serkangurel.recipecomposekmm.data.cache.RecipeCache
import com.serkangurel.recipecomposekmm.data.cache.RecipeCacheImpl
import com.serkangurel.recipecomposekmm.data.cache.RecipeDatabaseFactory
import com.serkangurel.recipecomposekmm.data.remote.client.RecipeHttpClient
import com.serkangurel.recipecomposekmm.data.remote.repository.RecipeRepository
import com.serkangurel.recipecomposekmm.data.remote.service.RecipeService
import com.serkangurel.recipecomposekmm.data.remote.service.RecipeServiceImpl
import com.serkangurel.recipecomposekmm.screens.MainComponent
import com.serkangurel.recipecomposekmm.screens.MainScreen
import com.serkangurel.recipecomposekmm.theme.KMMComposeTheme
import com.serkangurel.recipecomposekmm.util.DatetimeUtil
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.github.aakira.napier.log
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module

@Composable
internal fun RecipeApp(component: MainComponent) {

    KMMComposeTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            MainScreen(component)
        }
    }
}

internal fun recipeAppInit(appDeclaration: KoinAppDeclaration = {}) {

    Napier.base(DebugAntilog())

    log { "Application Started" }

    initKoin(
        appDeclaration
    )
}

private fun initKoin(
    appDeclaration: KoinAppDeclaration = {}
) {
    startKoin {
        appDeclaration()
        modules(
            utilModule,
            cacheModule,
            dataModule
        )
    }
}

private val utilModule = module {
    singleOf(::DatetimeUtil)
}

val cacheModule = module {
    single { RecipeDatabaseFactory(get()).createDatabase() }
    singleOf(::RecipeCacheImpl) bind RecipeCache::class
}

private val dataModule = module {
    single { RecipeHttpClient().build() }
    singleOf(::RecipeServiceImpl) bind RecipeService::class
    singleOf(::RecipeRepository)
}