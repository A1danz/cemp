package screens.leaderboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import component.TeamsLeaderboardComponent
import dev.icerock.moko.resources.compose.stringResource
import model.TeamModel
import theme.Theme
import component.CempTeamCard
import component.CempText
import component.ErrorBanner
import component.ProgressBarBanner
import utils.StringResHelper
import com.cemp.SharedRes.strings as StringRes

@Composable
fun TeamsLeaderboardScreen(
    component: TeamsLeaderboardComponent,
    modifier: Modifier = Modifier,
) {
    val state by component.state.subscribeAsState()

    TeamsLeaderboardContent(
        state = state,
        onIntent = component::onIntent,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamsLeaderboardContent(
    state: TeamsLeaderboardComponent.Model,
    onIntent: (TeamsLeaderboardComponent.Intent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    CempText(
                        text = stringResource(StringRes.feature_teams_leaderboard_title),
                        textStyle = Theme.typography.text28SemiBold,
                    )
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
                ProgressBarBanner()
            }

            state.isError -> {
                ErrorBanner()
            }

            else -> {
                TeamsList(
                    teams = state.teams,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    onTeamClicked = { onIntent(TeamsLeaderboardComponent.Intent.OnTeamClicked(it.id)) }
                )
            }
        }
    }
}

@Composable
fun TeamsList(
    teams: List<TeamModel>,
    onTeamClicked: (TeamModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = modifier.background(Theme.colors.mainBackgroundColor)
    ) {
        items(
            count = teams.size,
            key = { teams[it].id },
        ) { index ->
            with(teams[index]) {
                CempTeamCard(
                    teamPosition = (index + 1).toString(),
                    teamName = name,
                    teamImageUrl = imageUrl,
                    modifier = Modifier.fillMaxWidth(),
                    location = location.let { StringResHelper.toString(it) },
                    onClick = { onTeamClicked(this) },
                )
            }
        }
    }
}