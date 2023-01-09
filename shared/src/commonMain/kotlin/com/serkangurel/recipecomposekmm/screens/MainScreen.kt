package com.serkangurel.recipecomposekmm.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.serkangurel.recipecomposekmm.screens.detail.DetailComponent
import com.serkangurel.recipecomposekmm.screens.detail.DetailScreen
import com.serkangurel.recipecomposekmm.screens.home.HomeComponent
import com.serkangurel.recipecomposekmm.screens.home.HomeScreen

@OptIn(ExperimentalDecomposeApi::class)
@Composable
internal fun MainScreen(
    component: MainComponent,
    modifier: Modifier = Modifier,
) {
    val childStack by component.stack.subscribeAsState()

    Box(modifier = modifier) {
        Children(
            stack = childStack,
            modifier = Modifier.fillMaxSize(),
            animation = null,
        ) {
            when (val child = it.instance) {
                is HomeComponent -> HomeScreen(child)
                is DetailComponent -> DetailScreen(child)
            }
        }
    }
}