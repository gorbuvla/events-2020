package me.gorbuvla.events2020

import android.app.Application
import me.gorbuvla.events.di.eventsModule
import me.gorbuvla.map.di.mapModule
import me.gorbuvla.navigation.di.navigationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class EventsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        startKoin {
            androidContext(this@EventsApp)
            modules((mapModule + eventsModule + navigationModule).distinct())
        }
    }
}
