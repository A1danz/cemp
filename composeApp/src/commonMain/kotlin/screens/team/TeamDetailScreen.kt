package screens.team

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import component.TeamDetailsComponent

@Composable
fun TeamDetailScreen(
    component: TeamDetailsComponent,
    modifier: Modifier = Modifier,
) {
    val state by component.state.subscribeAsState()

    Column {
        Text("Is Error - ${state.isError}")
        Text("Is Loading - ${state.isLoading}")
        Text("Is team - ${state.team}")
        Text("Is matches - ${state.recentMatches}")
    }
}