package com.cemp.feature.auth.domain.model.error

sealed interface LoginError {
    class InvalidData(val invalidFieldsHolder: LoginInvalidFieldsHolder) : LoginError
    data object UserNotFound : LoginError
    data object IncorrectPassword : LoginError
    data object Unknown : LoginError
}