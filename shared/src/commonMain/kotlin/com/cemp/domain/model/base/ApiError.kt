package com.cemp.domain.model.base

sealed class ApiError(val errorBody: String?) {
    class UnknownError(val reason: Throwable) : ApiError(null)
    class UnknownHttpError(errorBody: String? = null) : ApiError(errorBody)
    class NotFoundError(errorBody: String? = null) : ApiError(errorBody)
    class InvalidDataError(errorBody: String? = null) : ApiError(errorBody)
    class AccessDeniedError(errorBody: String? = null) : ApiError(errorBody)
    class UnauthorizedError(errorBody: String? = null) : ApiError(errorBody)
    class ServerError(errorBody: String? = null) : ApiError(errorBody)
    class NetworkError : ApiError(null)
}

fun getApiErrorFromHttpCode(httpCode: Int, errorBody: String? = null): ApiError {
    return when (httpCode) {
        400 -> ApiError.InvalidDataError(errorBody)
        401 -> ApiError.UnauthorizedError(errorBody)
        403 -> ApiError.AccessDeniedError(errorBody)
        404 -> ApiError.NotFoundError(errorBody)
        in 500..599 -> ApiError.ServerError(errorBody)
        else -> ApiError.UnknownHttpError(errorBody)
    }
}