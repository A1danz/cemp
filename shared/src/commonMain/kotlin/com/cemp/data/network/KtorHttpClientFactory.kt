package com.cemp.data.network

import com.cemp.common.ext.logErr
import com.cemp.shared.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createHttpClient(
    engine: HttpClientEngineFactory<HttpClientEngineConfig>,
    json: Json,
): HttpClient {
    return HttpClient(engine) {
        install(Logging) {
            level = LogLevel.ALL

            logger = object : Logger {
                override fun log(message: String) {
                    println("HTTP Client: $message")
                }
            }
        }

        install(ContentNegotiation) {
            json(json)
        }

        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = BuildConfig.BASE_URL
            }

            header("Authorization", "Bearer ${BuildConfig.API_TOKEN}")
        }
    }
}