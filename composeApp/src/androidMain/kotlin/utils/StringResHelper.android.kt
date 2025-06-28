package utils

import android.content.Context
import dev.icerock.moko.resources.desc.StringDesc
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual object StringResHelper : KoinComponent {

    private val context by inject<Context>()

    actual fun toString(desc: StringDesc): String {
        return desc.toString(context)
    }
}