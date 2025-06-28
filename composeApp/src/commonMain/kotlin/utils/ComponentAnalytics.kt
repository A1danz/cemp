package utils

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.cemp.analytics.Analytics

fun <CONFIG : Any, CHILD : Any> Value<ChildStack<CONFIG, CHILD>>.enableAnalytics() {
    var lastActiveConfig: CONFIG? = null

    this.subscribe { childStack ->

        val activeConfig = childStack.active.configuration
        if (activeConfig != lastActiveConfig) {
            Analytics.logScreenView(screenName = activeConfig.toString())
            lastActiveConfig = activeConfig as CONFIG
        }
    }
}