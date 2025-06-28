package ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import theme.Theme
import com.cemp.SharedRes.images as ImageRes

@Composable
fun TeamBanner(
    teamName: String,
    teamImageUrl: String?,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(0.1.dp, Theme.colors.textColor),
        colors = CardDefaults.cardColors(
            containerColor = Theme.colors.secondaryBackgroundColor,
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = teamImageUrl ?: ImageRes.ic_cs,
                contentDescription = teamName,
                modifier = Modifier
                    .size(100.dp),
                colorFilter = ColorFilter.tint(Theme.colors.textColor)
                    .takeIf { teamImageUrl == null }
            )
            Spacer(modifier = Modifier.width(24.dp))
            CempText(
                text = teamName,
                textStyle = Theme.typography.text24Bold,
            )
        }
    }
}
