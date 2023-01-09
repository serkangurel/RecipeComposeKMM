@file:OptIn(KoinInternalApi::class)

package com.serkangurel.recipecomposekmm.data.ext

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.koin.core.Koin
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.core.scope.Scope
import org.koin.mp.KoinPlatformTools

@Composable
internal fun getKoin(): Koin = remember {
    KoinPlatformTools.defaultContext().get()
}

@Composable
internal inline fun <reified T> get(
    qualifier: Qualifier? = null,
    scope: Scope = KoinPlatformTools.defaultContext().get().scopeRegistry.rootScope,
    noinline parameters: ParametersDefinition? = null,
): T = remember(qualifier, parameters) {
    scope.get(qualifier, parameters)
}

internal inline fun <reified T> inject(
    qualifier: Qualifier? = null,
    scope: Scope = KoinPlatformTools.defaultContext().get().scopeRegistry.rootScope,
    noinline parameters: ParametersDefinition? = null,
): T = scope.get(qualifier, parameters)
