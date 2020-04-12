package me.gorbuvla.domain.di

import android.content.Context
import me.gorbuvla.domain.model.EventRepository
import me.gorbuvla.domain.model.EventRepositoryImpl
import me.gorbuvla.domain.model.PlaceRepository
import me.gorbuvla.domain.model.PlaceRepositoryImpl
import me.gorbuvla.domain.model.settings.UserSettings
import me.gorbuvla.domain.model.settings.UserSettingsImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val domainModule = module {

    single<EventRepository> {
        EventRepositoryImpl()
    }

    single<PlaceRepository> {
        PlaceRepositoryImpl()
    }

    single<UserSettings> {
        UserSettingsImpl(
            sharedPreferences = androidContext().getSharedPreferences("FILTERS", Context.MODE_PRIVATE),
            default = get()
        )
    }
}