package ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.stringResource
import model.PlayerModel
import theme.Theme
import utils.StringResHelper
import com.cemp.SharedRes.strings as StringRes

@Composable
fun TeamRoster(
    players: List<PlayerModel>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(
            count = players.size,
            key = { players[it].id }
        ) {
            players[it].run {
                PlayerRow(this)
            }
        }
    }
}

@Composable
fun PlayerRow(
    player: PlayerModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Theme.colors.secondaryBackgroundColor)
            .border(0.1.dp, Theme.colors.textColor)
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CempText(
            text = player.fullName.let { StringResHelper.toString(it) },
            textStyle = Theme.typography.text14Medium,
            modifier = Modifier.weight(0.4f),
        )
        CempText(
            text = player.name,
            textStyle = Theme.typography.text14Bold,
            modifier = Modifier.weight(0.4f),
        )
        CempText(
            text = player.nationality ?: stringResource(StringRes.common_no_info),
            textStyle = Theme.typography.text14Medium,
            modifier = Modifier.weight(0.2f),
            isBlue = true,
        )
    }
}