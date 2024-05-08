package com.example.cryptosep.domain.utils

import java.net.ProtocolException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

fun <T> checkError(throwable: Throwable): ResultState.Error<T> {
    return when (throwable) {
        is UnknownHostException -> {
            ResultState.Error(NO_INTERNET)
        }
        is SSLHandshakeException -> {
            ResultState.Error(SSL_HANDSHAKE)
        }
        is SocketTimeoutException -> {
            ResultState.Error(throwable.localizedMessage ?: "SocketTimeoutException")
        }
        is ProtocolException -> {
            ResultState.Error(throwable.localizedMessage ?: "ProtocolException")
        }
        else -> {
            ResultState.Error(throwable.localizedMessage ?: "Exception")
        }
    }
}
