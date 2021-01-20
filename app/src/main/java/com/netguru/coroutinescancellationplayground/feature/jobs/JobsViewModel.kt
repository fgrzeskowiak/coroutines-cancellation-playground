package com.netguru.coroutinescancellationplayground.feature.jobs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netguru.coroutinescancellationplayground.common.ViewState
import com.netguru.coroutinescancellationplayground.model.RocketLaunch
import com.netguru.coroutinescancellationplayground.repository.LaunchesRepository
import kotlinx.coroutines.*

class JobsViewModel(repository: LaunchesRepository) : ViewModel() {
    val launchesLiveData: LiveData<ViewState<List<RocketLaunch>, Throwable>> get() = _launchesLiveData

    private val _launchesLiveData = MutableLiveData<ViewState<List<RocketLaunch>, Throwable>>()

    init {
        val newJob = Job()
        viewModelScope.launch(newJob) {
            _launchesLiveData.postValue(ViewState.Loading)
            delay(1000)
            kotlin.runCatching { repository.getLaunches() }
                .onSuccess { _launchesLiveData.postValue(ViewState.Success(it)) }
                .onFailure { _launchesLiveData.postValue(ViewState.Error(it)) }
        }.invokeOnCompletion { exception ->
            exception?.let { _launchesLiveData.postValue(ViewState.Error(it)) }
        }
        newJob.cancel()

//        TODO uncomment to see that the above Job is not getting cancelled, because it's not connected to the scope
//        viewModelScope.cancel()

//        TODO uncomment to see proper use of Job objects
//        val newJob = viewModelScope.launch {
//            _launchesLiveData.postValue(ViewState.Loading)
//            delay(1000)
//            kotlin.runCatching { repository.getLaunches() }
//                .onSuccess { _launchesLiveData.postValue(ViewState.Success(it)) }
//                .onFailure { _launchesLiveData.postValue(ViewState.Error(it)) }
//        }
//        newJob.invokeOnCompletion { exception ->
//            exception?.let { _launchesLiveData.postValue(ViewState.Error(it)) }
//        }
//
//        viewModelScope.cancel()
//    }
    }
}
