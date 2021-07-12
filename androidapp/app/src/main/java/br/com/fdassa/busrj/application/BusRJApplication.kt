package br.com.fdassa.busrj.application

import android.app.Application
import com.orhanobut.hawk.Hawk
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BusRJApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        setupKoin()
        setupHawk()
    }

    private fun setupKoin() = startKoin {
        androidLogger(Level.DEBUG)
        androidContext(this@BusRJApplication)
        modules(appModule)
    }

    private fun setupHawk() = Hawk.init(this).build()
}