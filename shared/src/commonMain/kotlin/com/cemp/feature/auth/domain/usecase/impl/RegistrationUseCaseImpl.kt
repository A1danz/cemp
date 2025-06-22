package com.cemp.feature.auth.domain.usecase.impl

import com.cemp.feature.auth.domain.model.error.RegistrationError
import com.cemp.feature.auth.domain.model.result.RegistrationResult
import com.cemp.feature.auth.domain.repository.AuthRepository
import com.cemp.feature.auth.domain.usecase.RegistrationUseCase
import com.cemp.feature.auth.domain.validator.UserCredentialsValidator


class RegistrationUseCaseImpl(
    private val authRepository: AuthRepository,
    private val validator: UserCredentialsValidator,
) : RegistrationUseCase {
    override suspend operator fun invoke(
        name: String,
        username: String,
        email: String,
        password: String,
    ): RegistrationResult {
        val validationFields = validator.getRegistrationValidation(name, username, email, password)

        if (validationFields.hasInvalidFields()) {
            return RegistrationResult.Failed(
                error = RegistrationError.InvalidData(validationFields)
            )
        }

        return authRepository.register(name, username, email, password)
    }
}