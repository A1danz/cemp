package screens.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import component.AuthComponent

//import com.cemp.feature.auth.presentation.component.AuthComponent

@Composable
fun AuthScreen(
    component: AuthComponent,
    modifier: Modifier = Modifier,
) {
    val childStack by component.childStack.subscribeAsState()

    Children(stack = childStack, animation = stackAnimation(fade())) { child ->
        when (val instance = child.instance) {
            is AuthComponent.Child.Welcome -> WelcomeScreen(instance.component)
            is AuthComponent.Child.Login -> LoginScreen(instance.component)
            is AuthComponent.Child.Register -> RegisterScreen(instance.component)
        }
    }

}