package ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.MatchModel
import theme.Theme
import utils.StringResHelper

@Composable
fun MatchesList(
    matches: List<MatchModel>,
    modifier: Modifier = Modifier,
    onMatchClicked: (MatchModel) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.background(Theme.colors.mainBackgroundColor)
    ) {
        items(
            count = matches.size,
            key = { matches[it].id }
        ) { index ->
            matches[index].run {
                CempMatchCard(
                    tournament = tournamentName,
                    status = status.let { StringResHelper.toString() },
                    team1Name = firstTeam.name,
                    team1Image = firstTeam.imageUrl,
                    team2Name = secondTeam.name,
                    team2Image = secondTeam.imageUrl,
                    matchTime = startDate,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onMatchClicked(this) }
                )
            }
        }
    }
}