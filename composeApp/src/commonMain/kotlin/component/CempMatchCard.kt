package component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.icerock.moko.resources.compose.painterResource
import theme.Theme
import com.cemp.SharedRes.images as ImageRes

@Composable
fun CempMatchCard(
    tournament: String,
    status: String,
    team1Name: String,
    team2Name: String,
    team1Image: String?,
    team2Image: String?,
    matchTime: String?,
    onClick: () -> Unit = { },
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Theme.colors.secondaryBackgroundColor,
            ),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(0.1.dp, Theme.colors.textColor),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CempText(
                    text = tournament,
                    textStyle = Theme.typography.text12Medium,
                )

                CempText(
                    text = status,
                    textStyle = Theme.typography.text12Medium,
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Левая команда
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    val imageModifier = Modifier.size(34.dp)

                    team1Image?.let {
                        AsyncImage(
                            model = it,
                            contentDescription = team1Name,
                            modifier = imageModifier,
                        )
                    } ?: Image(
                        modifier = imageModifier,
                        painter = painterResource(ImageRes.ic_cs),
                        contentDescription = team1Name,
                        colorFilter = ColorFilter.tint(Theme.colors.textColor)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    CempText(
                        text = team1Name,
                        textStyle = Theme.typography.text14Bold,
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentWidth(Alignment.CenterHorizontally)
                            .padding(end = 8.dp), // небольшой отступ от картинки
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                // "vs" по центру
                CempText(
                    text = "vs",
                    textStyle = Theme.typography.text14SemiBold,
                    modifier = Modifier.padding(horizontal = 20.dp),
                )

                // Правая команда
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.End
                ) {
                    val imageModifier = Modifier.size(34.dp)

                    team2Image?.let {
                        AsyncImage(
                            model = it,
                            contentDescription = team1Name,
                            modifier = imageModifier,
                        )
                    } ?: Image(
                        painter = painterResource(ImageRes.ic_cs),
                        contentDescription = team2Name,
                        colorFilter = ColorFilter.tint(Theme.colors.textColor)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    CempText(
                        text = team2Name,
                        textStyle = Theme.typography.text14Bold,
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentWidth(Alignment.CenterHorizontally)
                            .padding(start = 8.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            if (matchTime != null) {
                CempText(
                    text = matchTime,
                    isBlue = true,
                    textStyle = Theme.typography.text12SemiBold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}