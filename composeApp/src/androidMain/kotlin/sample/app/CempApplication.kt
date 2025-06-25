package sample.app

import android.app.Application
import di.connectModules
import di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CempApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@CempApplication)
            androidLogger()
        }
    }
}