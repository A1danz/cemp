package screens.matches

import androidx.compose.foundation.background
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
import component.MatchesComponent
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import theme.Theme
import ui.component.CempText
import ui.component.ErrorBanner
import ui.component.MatchesList
import ui.component.ProgressBarBanner
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
                        text = stringResource(StringRes.feature_matches_title),
                        textStyle = Theme.typography.text28SemiBold,
                    )
                },
                actions = {
                    IconButton(onClick = { onIntent(MatchesComponent.Intent.OnLogoutClicked) }) {
                        Icon(
                            painter = painterResource(ImageRes.ic_logout),
                            contentDescription = stringResource(StringRes.common_logout),
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
        modifier = modifier
            .fillMaxSize()
            .background(Theme.colors.mainBackgroundColor)
    ) { paddingValues ->
        when {
            model.isLoading -> {
                ProgressBarBanner()
            }

            model.isError -> {
                ErrorBanner()
            }

            else -> {
                MatchesList(
                    matches = model.matches,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Theme.colors.mainBackgroundColor)
                        .padding(horizontal = 16.dp)
                        .padding(paddingValues),
                    onMatchClicked = { onIntent(MatchesComponent.Intent.OnMatchClicked(it)) }
                )
            }
        }
    }
}