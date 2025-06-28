package mapper

import com.cemp.domain.model.Country
import com.cemp.domain.model.Player
import com.cemp.domain.model.Team
import dev.icerock.moko.resources.desc.StringDesc
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
        location = this.location.getName()
    )
}

fun Country?.getName(): StringDesc {
    return this?.fullName?.desc() ?: StringRes.common_no_info.desc()
}

fun Player.toUi(): PlayerModel {
    return PlayerModel(
        id = this.id,
        name = this.name,
        firstName = firstName,
        secondName = secondName,
        nationality = this.nationality?.code,
        fullName = if (firstName == null && secondName == null) {
            StringRes.common_no_info.desc()
        } else {
            listOfNotNull(firstName, secondName).joinToString(" ").desc()
        },

        )
}