package com.serkangurel.recipecomposekmm.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seiko.imageloader.rememberAsyncImagePainter
import com.serkangurel.recipecomposekmm.data.model.domain.Recipe

@Composable
internal fun DetailScreen(
    component: DetailComponent
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Yemek Tarifleri")
                },
                navigationIcon = {
                    IconButton(
                        onClick = component.navigateToBack
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary
            )
        },
        content = {
            when (component.detailState.value) {
                is DetailState.Loading -> {
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
                is DetailState.Content -> {
                    DetailContent(
                        recipeItem = (component.detailState.value as DetailState.Content).recipe,
                        component = component
                    )
                }
                is DetailState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text((component.detailState.value as DetailState.Error).message)
                    }
                }
            }
        }
    )
}

@Composable
internal fun DetailContent(
    recipeItem: Recipe,
    component: DetailComponent
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
                painter = rememberAsyncImagePainter(recipeItem.featuredImage),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.padding(all = 8.dp),
                text = recipeItem.title.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = component.getIngredientsAsString(recipeItem.ingredients),
                lineHeight = 24.sp
            )
        }
    }
}