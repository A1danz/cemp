package di

import com.cemp.di.commonModule
import com.cemp.di.dataModule
import com.cemp.feature.auth.di.authModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun KoinApplication.connectModules() {
    modules(
        dataModule,
        commonModule,
        authModule,
    )
}

fun initKoin(appDeclaration: KoinAppDeclaration = {  }) {
    startKoin {
        connectModules()
        appDeclaration()
    }
}

fun doInitKoin() = initKoin()