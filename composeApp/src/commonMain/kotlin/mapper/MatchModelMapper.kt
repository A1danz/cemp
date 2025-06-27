package mapper

import com.cemp.common.ext.formatShortDateTime
import com.cemp.domain.model.Match
import com.cemp.domain.model.MatchStatus
import com.cemp.domain.model.MatchTeamInfo
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import model.MatchModel
import model.MatchTeamInfoModel
import com.cemp.SharedRes.strings as StringRes

fun Match.toUi(): MatchModel {
    return MatchModel(
        id = id,
        tournamentName = tournamentName,
        status = this.status.toUi(),
        firstTeam = this.firstTeamInfo.toUi(),
        secondTeam = this.secondTeamInfo.toUi(),
        startDate = this.startDate?.formatShortDateTime(),
    )
}

fun MatchStatus.toUi(): StringDesc {
    return when (this) {
        MatchStatus.CANCELLED -> StringRes.feature_matches_match_status_cancelled.desc()
        MatchStatus.FINISHED -> StringRes.feature_matches_match_status_finished.desc()
        MatchStatus.NOT_STARTED -> StringRes.feature_matches_match_status_upcoming.desc()
        MatchStatus.POSTPONED -> StringRes.feature_matches_match_status_postponed.desc()
        MatchStatus.RUNNING -> StringRes.feature_matches_match_status_running.desc()
    }
}

fun MatchTeamInfo.toUi(): MatchTeamInfoModel {
    return MatchTeamInfoModel(
        id = this.teamInMatch.id,
        score = this.score,
        name = this.teamInMatch.name,
        imageUrl = this.teamInMatch.imageUrl,
    )
}