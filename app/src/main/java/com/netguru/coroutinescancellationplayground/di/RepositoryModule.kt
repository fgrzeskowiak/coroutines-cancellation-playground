package com.netguru.coroutinescancellationplayground.di

import com.netguru.coroutinescancellationplayground.repository.LaunchesRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repositoryModule = module {
    single { LaunchesRepository(get()) }
}
