package screens.matches

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import component.MatchesComponent
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import model.MatchModel
import theme.Theme
import ui.component.CempMatchCard
import ui.component.CempText
import com.cemp.SharedRes.images as ImageRes
import com.cemp.SharedRes.strings as StringRes

@Composable
fun MatchesScreen(
    component: MatchesComponent,
    modifier: Modifier = Modifier
) {
    val state by component.state.subscribeAsState()

    MatchesScreenContent(
        model = state,
        onIntent = component::onIntent,
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchesScreenContent(
    model: MatchesComponent.Model,
    onIntent: (MatchesComponent.Intent) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    CempText(
                        text = "Matches",
                        textStyle = Theme.typography.text28SemiBold,
                    )
                },
                actions = {
                    IconButton(onClick = { onIntent(MatchesComponent.Intent.OnLogoutClicked) }) {
                        Icon(
                            painter = painterResource(ImageRes.ic_logout),
                            contentDescription = "Logout",
                            tint = Theme.colors.textColor,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier.background(Theme.colors.mainBackgroundColor)
            )
        },
        modifier = modifier.fillMaxSize().background(Theme.colors.mainBackgroundColor)
    ) { paddingValues ->
        when {
            model.isLoading -> {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize().background(Theme.colors.mainBackgroundColor)
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(48.dp),
                        color = Theme.colors.blueTextColor,
                    )
                }
            }

            model.isError -> {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize().background(Theme.colors.mainBackgroundColor)
                ) {
                    Text(
                        text = stringResource(StringRes.feature_auth_unknown_error)
                    )
                }

            }

            else -> {
                MatchesList(
                    matches = model.matches,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    onMatchClicked = { onIntent(MatchesComponent.Intent.OnMatchClicked(it)) }
                )
            }
        }
    }
}

@Composable
private fun MatchesList(
    matches: List<MatchModel>,
    modifier: Modifier = Modifier,
    onMatchClicked: (MatchModel) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
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
                    status = status.toString(LocalContext.current),
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