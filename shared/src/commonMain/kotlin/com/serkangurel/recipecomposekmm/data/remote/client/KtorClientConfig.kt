package com.serkangurel.recipecomposekmm.data.remote.client

import io.ktor.client.*

expect class KtorClientConfig() {
    fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient
}