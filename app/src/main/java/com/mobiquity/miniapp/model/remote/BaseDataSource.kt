package com.mobiquity.miniapp.model.remote

import com.mobiquity.miniapp.utils.Result
import retrofit2.Response
import java.lang.Exception

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return Result.success(body)
                }
            }
            return Result.error("${response.code()}" + " " + response.message())
        } catch (e: Exception) {
            return Result.error(e.message ?: e.toString())
        }
    }
}