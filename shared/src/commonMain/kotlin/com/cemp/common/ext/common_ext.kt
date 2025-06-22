package com.cemp.common.ext

fun <T> Boolean.transform(
    onTrue: T,
    onFalse: T,
): T {
    return if (this) onTrue else onFalse
}