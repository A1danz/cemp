package sample.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import component.impl.DefaultRootComponent
import screens.RootScreen
import theme.AppTheme

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootComponent = DefaultRootComponent(defaultComponentContext())

        setContent {
            AppTheme {
                RootScreen(rootComponent)
            }
        }
    }
}