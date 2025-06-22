package com.cemp.data.settings.model

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val id: Long,
    val name: String,
    val username: String,
    val email: String,
)