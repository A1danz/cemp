package ui.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun CempTextField(
    value: String,
    label: String,
    error: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier,
        isError = error != null,
        shape = RoundedCornerShape(16.dp), // Изменённая скруглённость
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color(0xFFF0F0F0), // Светлый фон
            focusedBorderColor = Color(0xFF3F51B5), // Подсветка при фокусе
            unfocusedBorderColor = Color.Gray,
            cursorColor = Color(0xFF3F51B5), // Цвет курсора
            focusedLabelColor = Color(0xFF3F51B5)
        ),
        singleLine = true,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
    )
    if (error != null) {
        Text(
            text = error,
            color = Color.Red,
        )
    }
}
