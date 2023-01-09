package com.serkangurel.recipecomposekmm.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.serkangurel.recipecomposekmm.components.RecipeCardItem
import com.serkangurel.recipecomposekmm.data.model.domain.Recipe

@Composable
internal fun HomeScreen(
    component: HomeComponent
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Yemek Tarifleri")
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary
            )
        },
        content = {
            when (component.homeState.value) {
                is HomeState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = Color.Blue,
                            strokeWidth = 5.dp
                        )
                    }
                }
                is HomeState.Content -> {
                    HomeContent(
                        itemList = (component.homeState.value as HomeState.Content).itemList
                    ) {
                        component.navigateToDetail(it)
                    }
                }
                is HomeState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text((component.homeState.value as HomeState.Error).message)
                    }
                }
            }
        }
    )
}

@Composable
internal fun HomeContent(
    itemList: List<Recipe>,
    onClick: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            contentPadding = PaddingValues(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(itemList) { item ->
                RecipeCardItem(
                    imageUrl = item.featuredImage,
                    title = item.title,
                    onClick = {
                        onClick.invoke(item.id)
                    }
                )
            }
        }
    }
}