package com.netguru.coroutinescancellationplayground.feature.exceptions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netguru.coroutinescancellationplayground.common.ViewState
import com.netguru.coroutinescancellationplayground.model.RocketLaunch
import com.netguru.coroutinescancellationplayground.repository.LaunchesRepository
import kotlinx.coroutines.*

class ExceptionsViewModel(repository: LaunchesRepository) : ViewModel() {
    val launchesLiveData: LiveData<ViewState<List<RocketLaunch>, Throwable>> get() = _launchesLiveData

    private val _launchesLiveData = MutableLiveData<ViewState<List<RocketLaunch>, Throwable>>()

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        _launchesLiveData.postValue(ViewState.Error(throwable))
    }

    init {
        viewModelScope.launch {
            // this method crashes the app due to exception propagation
            kotlin.runCatching { repository.getFailingLaunches(this) }
                .onSuccess { _launchesLiveData.postValue(ViewState.Success(it)) }
                .onFailure { _launchesLiveData.postValue(ViewState.Error(it)) }

//            TODO uncomment to make use of coroutineScope{} builder
//            kotlin.runCatching { repository.getFailingLaunches() }
//                .onSuccess { _launchesLiveData.postValue(ViewState.Success(it)) }
//                .onFailure { _launchesLiveData.postValue(ViewState.Error(it)) }

//            TODO uncomment to make use of supervisedScope{} builder
//            kotlin.runCatching { repository.getFailingLaunchesSupervised() }
//                .onSuccess { _launchesLiveData.postValue(ViewState.Success(it)) }
//                .onFailure { _launchesLiveData.postValue(ViewState.Error(it)) }
        }

//        TODO uncomment to make use of CoroutineExceptionHandler
//        viewModelScope.launch(exceptionHandler) {
//            kotlin.runCatching { repository.getFailingLaunches(this) }
//                .onSuccess { _launchesLiveData.postValue(ViewState.Success(it)) }
//                .onFailure { _launchesLiveData.postValue(ViewState.Error(it)) }
//        }
    }
}
