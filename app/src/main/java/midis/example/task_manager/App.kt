package midis.example.task_manager

import android.app.Application
import midis.example.task_manager.di.application
import midis.example.task_manager.di.mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(application + mainScreen)
        }
    }
}