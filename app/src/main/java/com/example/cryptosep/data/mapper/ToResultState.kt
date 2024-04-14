package com.example.cryptosep.data.mapper

import com.example.cryptosep.domain.utils.*
import com.example.cryptosep.domain.utils.ResultState
import retrofit2.Response

fun <F,T> toResultState(response: Response<F>, mapperCallback: MapperCallback<F,T>): ResultState<T> {
    return if (response.isSuccessful) {
        response.body()?.let {
            ResultState.Success(mapperCallback.map(it))
        } ?: run {
            ResultState.Error(EMPTY_BODY_ERROR)
        }
    } else {
        if (response.code() == 401){
            response.errorBody()?.let {
                if (it.toString().contains("Token is not valid")){
                    ResultState.Error(INVALID_TOKEN, null)
                }
            }
        }
        if (response.code().toString().startsWith("5")) {
            ResultState.Error(SERVER_ERROR)
        } else {
            ResultState.Error(response.errorBody()?.string() ?: DEFAULT_ERROR)
        }
    }
}
