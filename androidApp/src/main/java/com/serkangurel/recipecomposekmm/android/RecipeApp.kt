package com.serkangurel.recipecomposekmm.android

import android.app.Application
import com.serkangurel.recipecomposekmm.recipeAndroidAppInit
import org.koin.android.ext.koin.androidContext

class RecipeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        recipeAndroidAppInit {
            androidContext(this@RecipeApp)
        }
    }

}