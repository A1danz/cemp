package com.cemp.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module

val commonModule = module {
    single<CoroutineDispatcher> { Dispatchers.IO }
}