package com.serkangurel.recipecomposekmm.data.remote.client

import com.serkangurel.recipecomposekmm.util.Constants
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class RecipeHttpClient {
    fun build(): HttpClient = KtorClientConfig().let { clientConfig ->
        clientConfig.httpClient {
            defaultRequest {
                header("Authorization", Constants.TOKEN)
                host = Constants.BASE_URL
                url {
                    protocol = URLProtocol.HTTPS
                }
            }
            install(Logging) {
                level = LogLevel.HEADERS
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.v(tag = "HTTP Client", message = message)
                    }
                }
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }
}