package screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import component.MainComponent
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import io.ktor.util.reflect.instanceOf
import model.BottomBarItemsFactory
import screens.leaderboard.TeamsLeaderboardScreen
import screens.match.MatchDetailsScreen
import screens.matches.MatchesScreen
import screens.team.TeamDetailScreen
import theme.Theme

@Composable
fun MainScreen(
    component: MainComponent,
    modifier: Modifier = Modifier
) {
    MainScreenContent(component.childStack, component::onTabSelected)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    componentStack: Value<ChildStack<*, MainComponent.Child>>,
    onTabSelected: (MainComponent.Tab) -> Unit,
    modifier: Modifier = Modifier,
) {
    val childStack by componentStack.subscribeAsState()

    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Theme.colors.mainBackgroundColor,
            ) {
                BottomBarItemsFactory.create().forEach {
                    NavigationBarItem(
                        selected = childStack.active.instance.instanceOf(it.childKClass),
                        onClick = { onTabSelected(it.tab) },
                        icon = {
                            Icon(
                                painterResource(it.icon),
                                modifier = Modifier.size(30.dp),
                                contentDescription = stringResource(it.name)
                            )
                        },
                        label = { Text(stringResource(it.name)) },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent,
                            selectedIconColor = Theme.colors.textColor,
                            selectedTextColor = Theme.colors.textColor,
                            unselectedTextColor = Theme.colors.secondaryTextColor,
                            unselectedIconColor = Theme.colors.secondaryTextColor,
                        )
                    )
                }
            }
        },
    ) { innerPadding ->
        Children(
            stack = childStack,
            modifier = Modifier.padding(innerPadding)
        ) { child ->
            when (val instance = child.instance) {
                is MainComponent.Child.Matches -> MatchesScreen(instance.component)
                is MainComponent.Child.TeamsLeaderboard -> TeamsLeaderboardScreen(instance.component)
                is MainComponent.Child.MatchDetails -> MatchDetailsScreen(instance.component)
                is MainComponent.Child.TeamDetails -> TeamDetailScreen(instance.component)
            }
        }
    }
}