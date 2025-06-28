package utils

import dev.icerock.moko.resources.desc.StringDesc

expect object StringResHelper {
    fun toString(desc: StringDesc): String
}