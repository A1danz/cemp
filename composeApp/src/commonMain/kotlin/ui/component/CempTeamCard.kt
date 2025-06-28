package ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.icerock.moko.resources.compose.painterResource
import theme.Theme
import com.cemp.SharedRes.images as ImageRes

@Composable
fun CempTeamCard(
    teamPosition: String,
    teamName: String,
    teamImageUrl: String?,
    location: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .clickable { onClick() }
            .background(Theme.colors.secondaryBackgroundColor)
            .border(0.1.dp, Theme.colors.textColor)
            .padding(vertical = 8.dp, horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CempText(
            text = teamPosition,
            textStyle = Theme.typography.text14Bold,
        )
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val imageModifier = Modifier
                .size(34.dp)
                .padding(end = 8.dp)

            teamImageUrl?.let {
                AsyncImage(
                    model = it,
                    contentDescription = teamName,
                    modifier = imageModifier,
                )
            } ?: Image(
                modifier = imageModifier,
                painter = painterResource(ImageRes.ic_cs),
                contentDescription = teamName,
                colorFilter = ColorFilter.tint(Theme.colors.textColor),
            )

            CempText(
                text = teamName,
                textStyle = Theme.typography.text14Bold,
            )
        }
        CempText(
            text = location,
            textStyle = Theme.typography.text12Medium,
            modifier = Modifier.weight(0.2f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }

}