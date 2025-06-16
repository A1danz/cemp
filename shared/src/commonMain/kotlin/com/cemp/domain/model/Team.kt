package com.cemp.domain.model

data class Team(
    val id: Int,
    val name: String,
    val imageUrl: String?,
    val roster: List<Player>
)