package utils

import dev.icerock.moko.resources.desc.StringDesc

actual object StringResHelper {
    actual fun toString(desc: StringDesc): String {
        return desc.localized()
    }
}