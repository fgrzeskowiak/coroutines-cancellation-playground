package com.netguru.coroutinescancellationplayground.extensions

import androidx.lifecycle.MutableLiveData
import com.netguru.coroutinescancellationplayground.common.ViewState
import kotlinx.coroutines.delay

suspend fun <T: Any> MutableLiveData<ViewState<T, Throwable>>.runCatchingAndPost(action: suspend () -> T) {
    postValue(ViewState.Loading)
    kotlin.runCatching { action() }
        .onSuccess { postValue(ViewState.Success(it)) }
        .onFailure { postValue(ViewState.Error(it)) }
}
