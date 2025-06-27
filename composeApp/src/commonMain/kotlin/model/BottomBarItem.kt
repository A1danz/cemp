package model

import component.MainComponent
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.StringResource
import kotlin.reflect.KClass

data class BottomBarItem(
    val name: StringResource,
    val icon: ImageResource,
    val tab: MainComponent.Tab,
    val childKClass: KClass<out MainComponent.Child>,
)