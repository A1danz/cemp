package com.cemp.feature.auth.domain.usecase

import com.cemp.feature.auth.domain.model.result.LoginResult


interface LoginUseCase {
    suspend operator fun invoke(email: String, password: String): LoginResult
}