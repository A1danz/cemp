package com.cemp.feature.auth.domain.model.result

import com.cemp.feature.auth.domain.model.error.RegistrationError

sealed interface RegistrationResult {
    data object Success : RegistrationResult
    data class Failed(val error: RegistrationError) : RegistrationResult
}