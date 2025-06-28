package sample.app

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import component.impl.DefaultRootComponent
import di.doInitKoin
import screens.RootScreen
import theme.AppTheme
import java.awt.Dimension

fun main() = application {
    doInitKoin()

    val lifecycle = LifecycleRegistry()

    // Создаем root component
    val root = DefaultRootComponent(
        componentContext = DefaultComponentContext(lifecycle = lifecycle)
    )

    val windowState = rememberWindowState(width = 1200.dp, height = 800.dp)

    LifecycleController(lifecycle, windowState)

    Window(
        onCloseRequest = ::exitApplication,
        state = windowState,
        title = "Cemp - Турниры по CS",
        resizable = true
    ) {
        window.minimumSize = Dimension(800, 600)

        AppTheme {
            RootScreen(root)
        }
    }
}
