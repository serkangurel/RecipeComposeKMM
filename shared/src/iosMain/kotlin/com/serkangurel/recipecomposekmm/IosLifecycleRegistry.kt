package com.serkangurel.recipecomposekmm

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.arkivanov.essenty.lifecycle.stop
import com.serkangurel.recipecomposekmm.screens.MainComponent

class IosLifecycleRegistry {

    private var lifecycleRegistry: LifecycleRegistry = LifecycleRegistry()
    var mainComponent: MainComponent

    init {
        mainComponent = MainComponent(
            componentContext = DefaultComponentContext(lifecycle = lifecycleRegistry)
        )
        lifecycleRegistry.onCreate()
    }

    fun onResume() {
        lifecycleRegistry.resume()
    }

    fun onStop() {
        lifecycleRegistry.stop()
    }

    fun onDestroy() {
        lifecycleRegistry.onDestroy()
    }

}