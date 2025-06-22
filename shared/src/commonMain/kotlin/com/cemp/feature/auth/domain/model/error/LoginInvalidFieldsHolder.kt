package com.cemp.feature.auth.domain.model.error

data class LoginInvalidFieldsHolder(
    val email: InvalidField?,
    val password: InvalidField?,
) {
    fun hasInvalidFields(): Boolean {
        return email != null || password != null
    }
}