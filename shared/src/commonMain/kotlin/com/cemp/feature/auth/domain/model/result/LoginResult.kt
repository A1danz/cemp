package com.cemp.feature.auth.domain.model.result

import com.cemp.feature.auth.domain.model.error.LoginError


sealed interface LoginResult {
    data object Success : LoginResult
    data class Failed(val error: LoginError) : LoginResult
}