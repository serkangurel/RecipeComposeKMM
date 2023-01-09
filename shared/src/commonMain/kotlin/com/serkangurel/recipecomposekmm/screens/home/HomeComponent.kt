package com.serkangurel.recipecomposekmm.screens.home

import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.serkangurel.recipecomposekmm.data.ext.coroutineScope
import com.serkangurel.recipecomposekmm.data.ext.inject
import com.serkangurel.recipecomposekmm.data.model.domain.Recipe
import com.serkangurel.recipecomposekmm.data.remote.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class HomeComponent(
    componentContext: ComponentContext,
    val navigateToDetail: (Int) -> Unit,
) : ComponentContext by componentContext {

    private val recipeRepository: RecipeRepository = inject()
    private val scope = coroutineScope(Dispatchers.Default + SupervisorJob())
    val homeState = mutableStateOf<HomeState>(HomeState.Loading)

    init {
        getRecipes()
    }

    private fun getRecipes() = scope.launch {
        recipeRepository.searchRecipes(1, "",
            onSuccess = {
                homeState.value = HomeState.Content(it)
            },
            onFailure = {
                homeState.value = HomeState.Error(it.message ?: "Error State")
            })
    }
}

sealed class HomeState {
    object Loading : HomeState()
    data class Error(val message: String) : HomeState()
    data class Content(val itemList: List<Recipe>) : HomeState()
}