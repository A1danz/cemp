package model

import dev.icerock.moko.resources.desc.StringDesc

data class TeamModel(
    val id: Int,
    val name: String,
    val imageUrl: String?,
    val roster: List<PlayerModel>,
    val location: StringDesc,
)