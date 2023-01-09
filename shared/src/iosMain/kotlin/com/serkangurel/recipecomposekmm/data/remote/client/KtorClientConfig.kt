package com.serkangurel.recipecomposekmm.data.remote.client

import io.ktor.client.*
import io.ktor.client.engine.darwin.*

actual class KtorClientConfig {
    actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(Darwin) {
        config(this)
    }
}