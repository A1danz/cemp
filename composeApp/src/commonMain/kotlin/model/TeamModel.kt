package model

import com.cemp.domain.model.Player

data class TeamModel(
    val id: Int,
    val name: String,
    val imageUrl: String?,
    val roster: List<PlayerModel>
)