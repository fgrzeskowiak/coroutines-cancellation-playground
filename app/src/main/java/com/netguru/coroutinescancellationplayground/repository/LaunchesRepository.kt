package com.netguru.coroutinescancellationplayground.repository

import com.netguru.coroutinescancellationplayground.model.RocketLaunch
import com.netguru.coroutinescancellationplayground.network.LaunchesApi
import com.netguru.coroutinescancellationplayground.network.model.RocketLaunchResponse
import kotlinx.coroutines.*

class LaunchesRepository(private val launchesApi: LaunchesApi) {

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("exception handler $throwable")
    }

    suspend fun getFailingLaunches(scope: CoroutineScope): List<RocketLaunch> {
        val failingLaunches = scope.async { launchesApi.getFailingLaunches() }
        val launches = scope.async { launchesApi.getAllLaunches() }
        return awaitAll(failingLaunches, launches).flatMap { it.map(RocketLaunch::fromResponse) }
    }

    suspend fun getFailingLaunches(): List<RocketLaunch> = coroutineScope {
        val failingLaunches = async { launchesApi.getFailingLaunches() }
        val launches = async { launchesApi.getAllLaunches() }
        awaitAll(failingLaunches, launches).flatMap { it.map(RocketLaunch::fromResponse) }
    }

    suspend fun getFailingLaunchesSupervised(): List<RocketLaunch> = supervisorScope {
        val failingLaunches = async { launchesApi.getFailingLaunches() }
        val launches = async { launchesApi.getAllLaunches() }
        awaitAll(failingLaunches, launches).flatMap { it.map(RocketLaunch::fromResponse) }
    }

    suspend fun getLaunches() = launchesApi.getAllLaunches()
        .map(RocketLaunch::fromResponse)
}
