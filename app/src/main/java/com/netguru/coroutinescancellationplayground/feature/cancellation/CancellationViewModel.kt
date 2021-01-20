package com.netguru.coroutinescancellationplayground.feature.cancellation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netguru.coroutinescancellationplayground.common.ViewState
import com.netguru.coroutinescancellationplayground.model.RocketLaunch
import com.netguru.coroutinescancellationplayground.repository.LaunchesRepository
import kotlinx.coroutines.*

class CancellationViewModel(repository: LaunchesRepository) : ViewModel() {
    val launchesLiveData: LiveData<ViewState<List<RocketLaunch>, Throwable>> get() = _launchesLiveData

    private val _launchesLiveData = MutableLiveData<ViewState<List<RocketLaunch>, Throwable>>()

    init {
        viewModelScope.launch {
            runDataFetch {
                delay(1000)
            }
            runDataFetch {
                delay(1000)
            }
        }

        viewModelScope.cancel()
    }

    private suspend fun runDataFetch(action: suspend () -> Unit) {
        try {
            println("performing request")
            action()
        } catch (e: Exception) {
            println("exception: $e")

//            TODO uncomment to handle CancellationException properly
//            if (e is CancellationException) {
//                throw e
//            }
        }
    }

}
