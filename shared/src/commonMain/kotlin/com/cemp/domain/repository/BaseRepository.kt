package com.cemp.domain.repository

import io.ktor.client.request.HttpRequestBuilder

interface BaseRepository {
    companion object {
        val EMPTY_REQUEST_LOGIC: (HttpRequestBuilder.() -> Unit) = { }
    }
}