package model

import dev.icerock.moko.resources.desc.StringDesc

data class PlayerModel(
    val id: Int,
    val name: String,
    val firstName: String?,
    val secondName: String?,
    val fullName: StringDesc?,
    val nationality: String?,
)