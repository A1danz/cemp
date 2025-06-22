package com.cemp.feature.auth.domain.model.error

sealed interface RegistrationError {
    data class InvalidData(val registrationInvalidFieldsHolder: RegistrationInvalidFieldsHolder) :
        RegistrationError

    data object UserAlreadyExists : RegistrationError
    data object Unknown : RegistrationError
}