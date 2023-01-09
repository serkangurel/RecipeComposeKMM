package com.serkangurel.recipecomposekmm.screens.detail

import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.serkangurel.recipecomposekmm.data.ext.coroutineScope
import com.serkangurel.recipecomposekmm.data.ext.inject
import com.serkangurel.recipecomposekmm.data.model.domain.Recipe
import com.serkangurel.recipecomposekmm.data.remote.repository.RecipeRepository
import com.serkangurel.recipecomposekmm.screens.MainComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class DetailComponent(
    componentContext: ComponentContext,
    config: MainComponent.Config.DetailConfig,
    val navigateToBack: () -> Unit
) : ComponentContext by componentContext {

    private val recipeRepository: RecipeRepository = inject()
    private val scope = coroutineScope(Dispatchers.Default + SupervisorJob())
    val detailState = mutableStateOf<DetailState>(DetailState.Loading)

    init {
        getRecipeById(config.recipeId)
    }

    private fun getRecipeById(recipeId: Int) = scope.launch {
        recipeRepository.getRecipe(recipeId,
            onSuccess = {
                detailState.value = DetailState.Content(it)
            },
            onFailure = {
                detailState.value = DetailState.Error(it.message ?: "Error State")
            })
    }

    fun getIngredientsAsString(list: List<String>?): String {
        return list?.let {
            val sb = StringBuilder()
            list.forEach {
                sb.appendLine(it)
            }
            return sb.toString()
        } ?: ""
    }
}

sealed class DetailState {
    object Loading : DetailState()
    data class Error(val message: String) : DetailState()
    data class Content(val recipe: Recipe) : DetailState()
}