package screens.team

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.cemp.SharedRes.images
import com.cemp.SharedRes.strings
import component.TeamDetailsComponent
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import model.MatchModel
import theme.Theme
import ui.component.CempText
import ui.component.ErrorBanner
import ui.component.MatchesList
import ui.component.ProgressBarBanner
import ui.component.TeamBanner
import ui.component.TeamRosterBlock
import com.cemp.SharedRes.strings as StringRes

@Composable
fun TeamDetailScreen(
    component: TeamDetailsComponent,
    modifier: Modifier = Modifier,
) {
    val state by component.state.subscribeAsState()

    TeamDetailsContent(
        state = state,
        onIntent = { component.onIntent(it) },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamDetailsContent(
    state: TeamDetailsComponent.Model,
    onIntent: (TeamDetailsComponent.Intent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    CempText(
                        text = stringResource(strings.feature_team_detail_title),
                        textStyle = Theme.typography.text28SemiBold,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onIntent(TeamDetailsComponent.Intent.BackClicked) }) {
                        Icon(
                            painter = painterResource(images.ic_left_arrow),
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
            state.isLoading -> {
                ProgressBarBanner(modifier = Modifier.padding(paddingValues))
            }

            state.isError -> {
                ErrorBanner(modifier = Modifier.padding(paddingValues))
            }

            else -> {
                state.team?.let { team ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Theme.colors.mainBackgroundColor)
                            .padding(paddingValues)
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(24.dp),
                    ) {
                        TeamBanner(
                            teamName = team.name,
                            teamImageUrl = team.imageUrl,
                        )
                        TeamRosterBlock(
                            name = stringResource(StringRes.common_roster),
                            players = team.roster,
                        )
                        RecentMatchesBlock(
                            matches = state.recentMatches,
                            onMatchClicked = { onIntent(TeamDetailsComponent.Intent.MatchClicked(it)) }
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun RecentMatchesBlock(
    matches: List<MatchModel>,
    modifier: Modifier = Modifier,
    onMatchClicked: (MatchModel) -> Unit,
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        CempText(
            text = stringResource(StringRes.feature_team_recent_matches),
            textStyle = Theme.typography.text20SemiBold,
        )

        MatchesList(
            matches = matches,
        ) {}

    }
}