package com.cemp.feature.auth.domain.validator

import com.cemp.feature.auth.domain.model.error.InvalidField
import com.cemp.feature.auth.domain.model.error.LoginInvalidFieldsHolder
import com.cemp.feature.auth.domain.model.error.RegistrationInvalidFieldsHolder

interface UserCredentialsValidator {
    fun getRegistrationValidation(
        name: String,
        username: String,
        email: String,
        password: String
    ): RegistrationInvalidFieldsHolder

    fun getLoginValidation(
        email: String,
        password: String
    ): LoginInvalidFieldsHolder

    fun getNameValidation(name: String): InvalidField?
    fun getUsernameValidation(username: String): InvalidField?
    fun getEmailValidation(email: String): InvalidField?
    fun getPasswordValidation(password: String): InvalidField?
}