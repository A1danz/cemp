package screens.leaderboard

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import component.TeamsLeaderboardComponent

@Composable
fun TeamsLeaderboardScreen(
    component: TeamsLeaderboardComponent,
    modifier: Modifier = Modifier,
) {
    val state by component.state.subscribeAsState()

    Column {
        Text("Error - ${state.isError}")
        Text("Loading - ${state.isLoading}")
        Text("Data - ${state.teams}")
    }
}