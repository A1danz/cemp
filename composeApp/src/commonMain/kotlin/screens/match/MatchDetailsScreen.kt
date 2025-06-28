package screens.match

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import component.MatchDetailsComponent

@Composable
fun MatchDetailsScreen(
    component: MatchDetailsComponent,
    modifier: Modifier = Modifier,
) {
    val state by component.state.subscribeAsState()

    Column {
        Text("Is loading - ${state.isLoading}")
        Text("Is error - ${state.isError}")
        Text("Is match - ${state.match}")
        Text("Is team1 - ${state.firstTeamPlayers}")
        Text("Is team2 - ${state.secondTeamsPlayers}")
    }

}