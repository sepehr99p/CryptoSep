package com.example.cryptosep.ui.utils

sealed class DataState<T>(val data : T?) {
    class LoadingState<T>(data: T) : DataState<T>(data)
    class LoadedState<T>(data: T?) : DataState<T>(data)
    class FailedState<T>(data: T?) : DataState<T>(data)
}