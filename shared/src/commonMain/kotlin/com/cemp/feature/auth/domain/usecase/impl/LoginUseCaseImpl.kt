package com.cemp.feature.auth.domain.usecase.impl

import com.cemp.feature.auth.domain.model.error.LoginError
import com.cemp.feature.auth.domain.model.result.LoginResult
import com.cemp.feature.auth.domain.repository.AuthRepository
import com.cemp.feature.auth.domain.usecase.LoginUseCase
import com.cemp.feature.auth.domain.validator.UserCredentialsValidator


class LoginUseCaseImpl(
    private val repository: AuthRepository,
    private val validator: UserCredentialsValidator,
) : LoginUseCase {
    override suspend operator fun invoke(email: String, password: String): LoginResult {
        val validationFields = validator.getLoginValidation(email, password)

        if (validationFields.hasInvalidFields()) {
            return LoginResult.Failed(
                error = LoginError.InvalidData(validationFields)
            )
        }

        return repository.login(email, password)
    }
}