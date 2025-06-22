package com.cemp.feature.auth.domain.usecase

import com.cemp.feature.auth.domain.model.result.RegistrationResult


interface RegistrationUseCase {
    suspend operator fun invoke(
        name: String,
        username: String,
        email: String,
        password: String,
    ): RegistrationResult
}