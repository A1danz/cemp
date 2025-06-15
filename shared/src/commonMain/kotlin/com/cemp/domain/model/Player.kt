package com.cemp.domain.model

data class Player(
    val id: Int,
    val name: String,
    val firstName: String?,
    val secondName: String?,
    val nationality: Country?,
)