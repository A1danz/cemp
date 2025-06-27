package ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import theme.Theme

@Composable
fun CempTextField(
    value: String,
    label: String,
    error: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    textStyle: TextStyle = Theme.typography.text14Medium,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = modifier,
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = label, style = textStyle, color = Theme.colors.textColor) },
            isError = !error.isNullOrBlank(),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Theme.colors.textColor,
                unfocusedTextColor = Theme.colors.textColor,
                disabledTextColor = Theme.colors.textColor,

                focusedContainerColor = Theme.colors.secondaryBackgroundColor,
                unfocusedContainerColor = Theme.colors.secondaryBackgroundColor,
                disabledContainerColor = Theme.colors.secondaryBackgroundColor,
                errorContainerColor = Theme.colors.secondaryBackgroundColor,

                focusedIndicatorColor = Theme.colors.textColor,
                unfocusedIndicatorColor = Theme.colors.textColor,
                disabledIndicatorColor = Theme.colors.textColor,

                errorTextColor = Color.Red,
            ),

            textStyle = textStyle,
            singleLine = true,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
        )
        if (!error.isNullOrBlank()) {
            Text(
                text = error,
                color = Color.Red,
            )
        }
    }

}
