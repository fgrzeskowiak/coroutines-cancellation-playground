package com.netguru.coroutinescancellationplayground.di

import com.netguru.coroutinescancellationplayground.feature.LaunchesAdapter
import com.netguru.coroutinescancellationplayground.feature.cancellation.CancellationViewModel
import com.netguru.coroutinescancellationplayground.feature.exceptions.ExceptionsViewModel
import com.netguru.coroutinescancellationplayground.feature.jobs.JobsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
    factory { LaunchesAdapter() }
    viewModel { ExceptionsViewModel(get()) }
    viewModel { JobsViewModel(get()) }
    viewModel { CancellationViewModel(get()) }
}
