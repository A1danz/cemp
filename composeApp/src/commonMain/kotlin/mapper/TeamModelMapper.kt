package mapper

import com.cemp.domain.model.Player
import com.cemp.domain.model.Team
import dev.icerock.moko.resources.desc.desc
import model.PlayerModel
import model.TeamModel
import com.cemp.SharedRes.strings as StringRes

fun Team.toUi(): TeamModel {
    return TeamModel(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
        roster = this.roster.map { it.toUi() },
        location = this.location?.fullName?.desc() ?: StringRes.common_location_no_info.desc()
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