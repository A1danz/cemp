package component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface AuthComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {
        data class Welcome(val component: WelcomeComponent) : Child
        data class Login(val component: LoginComponent) : Child
        data class Register(val component: RegisterComponent) : Child
    }
}
