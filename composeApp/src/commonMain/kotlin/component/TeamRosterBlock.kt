package ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cemp.SharedRes.strings
import dev.icerock.moko.resources.compose.stringResource
import model.PlayerModel
import theme.Theme

@Composable
fun TeamRosterBlock(
    name: String,
    players: List<PlayerModel>,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        CempText(
            text = name,
            textStyle = Theme.typography.text20SemiBold,
        )

        if (players.isEmpty()) {
            CempText(
                text = stringResource(strings.common_no_info),
                textStyle = Theme.typography.text14Medium,
                isBlue = true,
            )
        } else {
            TeamRoster(players)
        }
    }
}