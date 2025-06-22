package com.cemp.common.ext

import co.touchlab.kermit.Logger

private val ERROR_LOGGER = Logger.withTag("ERROR")

fun logErr(msg: String, ex: Throwable? = null) {
    ERROR_LOGGER.e(ex) { msg }
}