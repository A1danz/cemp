package com.cemp.feature.auth.domain.repository

import com.cemp.feature.auth.domain.model.result.LoginResult
import com.cemp.feature.auth.domain.model.result.RegistrationResult


interface AuthRepository {
    suspend fun login(email: String, password: String): LoginResult
    suspend fun register(
        name: String,
        username: String,
        email: String,
        password: String,
    ): RegistrationResult
}