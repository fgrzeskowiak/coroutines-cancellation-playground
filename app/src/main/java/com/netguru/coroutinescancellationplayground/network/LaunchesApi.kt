package com.netguru.coroutinescancellationplayground.network

import com.netguru.coroutinescancellationplayground.network.model.RocketLaunchResponse
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.delay

class LaunchesApi(private val httpClient: HttpClient) {
    suspend fun getAllLaunches(): List<RocketLaunchResponse> {
        delay(100)
        return httpClient.get(LAUNCHES_SUFFIX)
    }

    suspend fun getFailingLaunches(): List<RocketLaunchResponse> {
        delay(100)
        throw RuntimeException("API Failed")
    }

    companion object {
        private const val LAUNCHES_SUFFIX = "v3/launches"
    }
}
