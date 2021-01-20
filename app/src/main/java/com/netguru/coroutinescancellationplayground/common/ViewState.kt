package com.netguru.coroutinescancellationplayground.common

sealed class ViewState<out RESULT : Any, out ERROR : Throwable> {
    data class Success<out RESULT : Any>(val data: RESULT) : ViewState<RESULT, Nothing>()
    data class Error<out ERROR : Throwable>(val error: ERROR) : ViewState<Nothing, ERROR>()

    object Loading : ViewState<Nothing, Nothing>()

    val isSuccess get() = this is Success
    val isError get() = this is Error
    val isLoading get() = this is Loading

    inline fun onSuccess(action: (RESULT) -> Unit): ViewState<RESULT, ERROR> {
        if (this is Success) action(data)
        return this
    }

    inline fun onError(action: (ERROR) -> Unit): ViewState<RESULT, ERROR> {
        if (this is Error) action(error)
        return this
    }
}
