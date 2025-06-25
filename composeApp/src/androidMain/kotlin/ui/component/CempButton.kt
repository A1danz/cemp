package ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.Button as MaterialButton

@Composable
fun CempButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
     MaterialButton(
         onClick = onClick,
         modifier = modifier,
         shape = RoundedCornerShape(20.dp),
         colors = ButtonDefaults.buttonColors(
             backgroundColor = Color.White,
         ),
         contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp)
     ) {
        Text(text, fontSize = 16.sp, color = Color.Black)
     }
}

@Preview
@Composable
fun CempButtonPreview() {
    CempButton("Test text") { }
}