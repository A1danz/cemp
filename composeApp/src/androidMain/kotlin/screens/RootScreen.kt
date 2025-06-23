package screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.cemp.presntation.component.RootComponent
import screens.auth.AuthScreen

@Composable
fun RootScreen(component: RootComponent) {
    val childStack by component.childStack.subscribeAsState()

    Children(stack = childStack, animation = stackAnimation(fade())) { child ->
        when(val instance = child.instance) {
            is RootComponent.Child.Auth -> AuthScreen(instance.component)
        }
    }
}