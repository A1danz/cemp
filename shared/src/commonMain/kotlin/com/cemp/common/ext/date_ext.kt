package com.cemp.common.ext

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun Instant.formatShortDateTime(timeZone: TimeZone = TimeZone.currentSystemDefault()): String {
    val local = this.toLocalDateTime(timeZone)
    val month = local.month.name.lowercase().replaceFirstChar { it.uppercaseChar() }.take(3)
    val day = local.dayOfMonth
    val hour = local.hour.toString().padStart(2, '0')
    val minute = local.minute.toString().padStart(2, '0')
    return "$month $day $hour:$minute"
}