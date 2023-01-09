package com.serkangurel.recipecomposekmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import com.serkangurel.recipecomposekmm.RecipeAndroidApp
import com.serkangurel.recipecomposekmm.screens.MainComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val component = MainComponent(
            componentContext = defaultComponentContext(),
        )

        setContent {
            RecipeAndroidApp(component)
        }
    }
}