package com.cemp.feature.auth.domain.model.error

data class RegistrationInvalidFieldsHolder(
    val name: InvalidField?,
    val username: InvalidField?,
    val email: InvalidField?,
    val password: InvalidField?,
) {
    fun hasInvalidFields(): Boolean {
        return name != null || username != null || email != null || password != null
    }
}