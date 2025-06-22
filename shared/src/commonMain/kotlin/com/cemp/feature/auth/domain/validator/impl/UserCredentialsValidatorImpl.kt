package com.cemp.feature.auth.domain.validator.impl

import com.cemp.feature.auth.domain.model.error.InvalidField
import com.cemp.feature.auth.domain.model.error.LoginInvalidFieldsHolder
import com.cemp.feature.auth.domain.model.error.RegistrationInvalidFieldsHolder
import com.cemp.feature.auth.domain.validator.UserCredentialsValidator

class UserCredentialsValidatorImpl : UserCredentialsValidator {

    override fun getRegistrationValidation(
        name: String,
        username: String,
        email: String,
        password: String
    ): RegistrationInvalidFieldsHolder {
        return RegistrationInvalidFieldsHolder(
            name = getNameValidation(name),
            username = getUsernameValidation(username),
            email = getEmailValidation(email),
            password = getPasswordValidation(password),
        )
    }

    override fun getLoginValidation(
        email: String,
        password: String
    ): LoginInvalidFieldsHolder {
        return LoginInvalidFieldsHolder(
            email = getEmailValidation(email),
            password = getPasswordValidation(password),
        )
    }

    override fun getNameValidation(name: String): InvalidField? {
        return (IntRange(MIN_NAME_LENGTH, MAX_NAME_LENGTH)).let { nameLengthRange ->
            if (nameLengthRange.contains(name.length).not()) {
                InvalidField.LengthRangeRule(nameLengthRange)
            } else {
                null
            }
        }
    }

    override fun getUsernameValidation(username: String): InvalidField? {
        return (IntRange(MIN_USERNAME_LENGTH, MAX_USERNAME_LENGTH)).let { usernameLengthRange ->
            if (usernameLengthRange.contains(username.length).not()) {
                InvalidField.LengthRangeRule(usernameLengthRange)
            } else {
                null
            }
        }
    }

    override fun getEmailValidation(email: String): InvalidField? {
        return when {
            email.length > MAX_EMAIL_LENGTH -> InvalidField.MaxLengthRule(MAX_EMAIL_LENGTH)
            !email.contains("@") -> InvalidField.MaskRule("example@example.com")
            else -> null
        }
    }

    override fun getPasswordValidation(password: String): InvalidField? {
        return (IntRange(MIN_PASSWORD_LENGTH, MAX_PASSWORD_LENGTH)).let { passwordLengthRange ->
            if (passwordLengthRange.contains(password.length).not()) {
                InvalidField.LengthRangeRule(passwordLengthRange)
            } else {
                null
            }
        }
    }

    companion object {
        const val MIN_NAME_LENGTH = 3
        const val MAX_NAME_LENGTH = 50
        const val MIN_USERNAME_LENGTH = 5
        const val MAX_USERNAME_LENGTH = 20
        const val MAX_EMAIL_LENGTH = 255
        const val MIN_PASSWORD_LENGTH = 8
        const val MAX_PASSWORD_LENGTH = 40
    }
}