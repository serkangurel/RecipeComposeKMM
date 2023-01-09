package com.serkangurel.recipecomposekmm

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.ImageLoaderBuilder
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.cache.disk.DiskCacheBuilder
import com.seiko.imageloader.cache.memory.MemoryCacheBuilder
import com.serkangurel.recipecomposekmm.screens.MainComponent
import okio.Path.Companion.toOkioPath

@Composable
fun Activity.RecipeAndroidApp(component: MainComponent) {
    CompositionLocalProvider(
        LocalImageLoader provides generateImageLoader()
    ) {
        RecipeApp(component)
    }
}

private fun Activity.generateImageLoader(): ImageLoader {
    return ImageLoaderBuilder(this)
        .memoryCache {
            MemoryCacheBuilder(this)
                // Set the max size to 25% of the app's available memory.
                .maxSizePercent(0.25)
                .build()
        }
        .diskCache {
            DiskCacheBuilder()
                .directory(cacheDir.resolve("image_cache").toOkioPath())
                .maxSizeBytes(512L * 1024 * 1024) // 512MB
                .build()
        }
        .build()
}