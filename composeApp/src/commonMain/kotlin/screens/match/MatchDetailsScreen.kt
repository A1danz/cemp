package screens.match

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import component.MatchDetailsComponent
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import model.MatchModel
import model.MatchTeamInfoModel
import theme.Theme
import ui.component.CempText
import ui.component.ErrorBanner
import ui.component.ProgressBarBanner
import ui.component.TeamRosterBlock
import utils.StringResHelper
import com.cemp.SharedRes.images as ImageRes
import com.cemp.SharedRes.strings as StringRes

@Composable
fun MatchDetailsScreen(
    component: MatchDetailsComponent,
    modifier: Modifier = Modifier,
) {
    val state by component.state.subscribeAsState()

    MatchDetailsScreenContent(
        state = state,
        onIntent = { component.onIntent(it) },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchDetailsScreenContent(
    state: MatchDetailsComponent.Model,
    onIntent: (MatchDetailsComponent.Intent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    CempText(
                        text = stringResource(StringRes.feature_match_details_title),
                        textStyle = Theme.typography.text24Medium,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onIntent(MatchDetailsComponent.Intent.BackClicked) }) {
                        Icon(
                            painter = painterResource(ImageRes.ic_left_arrow),
                            contentDescription = stringResource(StringRes.common_back),
                            tint = Theme.colors.textColor,
                            modifier = Modifier.size(20.dp),
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier.background(Theme.colors.mainBackgroundColor)
            )
        },
        modifier = modifier
            .fillMaxSize()
            .background(Theme.colors.mainBackgroundColor)
    ) { paddingValues ->
        when {
            state.isError -> {
                ErrorBanner(Modifier.padding(paddingValues))
            }

            state.isLoading -> {
                ProgressBarBanner(Modifier.padding(paddingValues))
            }

            else -> {
                state.match?.let { match ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Theme.colors.mainBackgroundColor)
                            .padding(paddingValues)
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        MatchDetailsHeader(match)
                        MatchScore(
                            match = match,
                            onTeamClicked = { onIntent(MatchDetailsComponent.Intent.OnTeamClicked(it)) },
                        )
                        TeamRosterBlock(
                            name = state.match.firstTeam.name,
                            players = state.firstTeamPlayers
                        )
                        TeamRosterBlock(
                            name = state.match.secondTeam.name,
                            players = state.secondTeamsPlayers
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MatchDetailsHeader(
    match: MatchModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CempText(
            text = match.tournamentName,
            textStyle = Theme.typography.text24Bold,
        )
        CempText(
            text = if (match.startDate != null) {
                "${match.startDate} - ${match.status.let { StringResHelper.toString(it) }}"
            } else {
                match.status.let { StringResHelper.toString(it) }
            },
            isBlue = true,
            textStyle = Theme.typography.text16SemiBold,
        )
    }
}

@Composable
fun MatchScore(
    match: MatchModel,
    onTeamClicked: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        MatchTeam(
            team = match.firstTeam,
            onClick = { onTeamClicked(it) }
        )
        CempText(
            text = "${match.firstTeam.score} - ${match.secondTeam.score}",
            textStyle = Theme.typography.text36ExtraBold,
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.CenterHorizontally),
        )
        MatchTeam(
            team = match.secondTeam,
            onClick = { onTeamClicked(it) }
        )
    }
}

@Composable
fun MatchTeam(
    team: MatchTeamInfoModel,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable { onClick(team.id) },
    ) {
        AsyncImage(
            model = team.imageUrl ?: ImageRes.ic_cs,
            contentDescription = team.name,
            colorFilter = ColorFilter.tint(Theme.colors.textColor).takeIf { team.imageUrl == null },
            modifier = Modifier.size(64.dp)
        )

        CempText(
            text = team.name,
            textStyle = Theme.typography.text14Bold
        )
    }
}