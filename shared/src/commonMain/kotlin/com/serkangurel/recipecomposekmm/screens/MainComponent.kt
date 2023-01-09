package com.serkangurel.recipecomposekmm.screens

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackCallback
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.serkangurel.recipecomposekmm.screens.detail.DetailComponent
import com.serkangurel.recipecomposekmm.screens.home.HomeComponent

class MainComponent(
    componentContext: ComponentContext,
) : ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    private val _stack = childStack(
        source = navigation,
        initialStack = { listOf(Config.HomeConfig) },
        childFactory = ::createScreenComponent,
    )

    val stack: Value<ChildStack<*, ComponentContext>>
        get() = _stack

    private val backCallback = BackCallback(isEnabled = true) {
        navigation.pop()
    }

    init {
        backHandler.register(backCallback)

        stack.subscribe {
            backCallback.isEnabled = it.active.instance !is HomeComponent
        }
    }

    private fun createScreenComponent(
        config: Config,
        context: ComponentContext,
    ) = when (config) {
        is Config.HomeConfig -> HomeComponent(context) { recipeId ->
            navigation.push(Config.DetailConfig(recipeId))
        }
        is Config.DetailConfig -> DetailComponent(context, config) {
            navigation.pop()
        }
    }

    sealed class Config : Parcelable {
        @Parcelize
        object HomeConfig : Config()

        @Parcelize
        data class DetailConfig(
            val recipeId: Int
        ) : Config()
    }

}