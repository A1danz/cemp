package com.cemp.data.network

import com.cemp.common.ext.logErr
import com.cemp.domain.model.base.ApiError
import com.cemp.domain.model.base.NetworkResponse
import com.cemp.domain.model.base.getApiErrorFromHttpCode
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess

suspend inline fun <reified T> makeSafeApiCall(
    apiCall: () -> HttpResponse,
): NetworkResponse<T> {
    val resp = runCatching {
        apiCall()
    }.getOrElse {
        logErr("Network err", it)
        return NetworkResponse.Error(ApiError.UnknownError(it))
    }

    if (!resp.status.isSuccess()) {
        return NetworkResponse.Error(getApiErrorFromHttpCode(resp.status.value, resp.bodyAsText()))
    }

    return NetworkResponse.Success(resp.body())
}