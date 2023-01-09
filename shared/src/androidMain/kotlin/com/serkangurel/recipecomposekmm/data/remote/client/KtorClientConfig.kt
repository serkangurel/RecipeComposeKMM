package com.serkangurel.recipecomposekmm.data.remote.client

import io.ktor.client.*
import io.ktor.client.engine.android.*

actual class KtorClientConfig {
    actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(Android) {
        config(this)
    }
}