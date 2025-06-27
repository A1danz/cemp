package mapper

import com.cemp.domain.model.Player
import com.cemp.domain.model.Team
import model.PlayerModel
import model.TeamModel

fun Team.toUi(): TeamModel {
    return TeamModel(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
        roster = this.roster.map { it.toUi() },
    )
}

fun Player.toUi(): PlayerModel {
    return PlayerModel(
        id = this.id,
        name = this.name,
        firstName = this.firstName,
        secondName = this.secondName,
        nationality = this.nationality?.fullName,
    )
}