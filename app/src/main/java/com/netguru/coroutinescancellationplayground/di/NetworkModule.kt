package com.netguru.coroutinescancellationplayground.di

import com.netguru.coroutinescancellationplayground.network.LaunchesApi
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import org.koin.dsl.module

val networkModule = module {
    single { createHttpClient() }
    single { LaunchesApi(get()) }
}

private fun createHttpClient() =
    HttpClient {
        defaultRequest {
            host = LAUNCHES_BASE_URL
            url {
                protocol = URLProtocol.HTTPS
            }
        }
        Json {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
            })
        }
        Logging {
            logger = Logger.SIMPLE
            level = LogLevel.BODY
        }
    }

private const val LAUNCHES_BASE_URL = "api.spacexdata.com"
