package me.gorbuvla.map.di

import me.gorbuvla.domain.di.domainModule
import me.gorbuvla.domain.model.settings.SettingsConfig
import me.gorbuvla.map.screens.filter.FiltersViewModel
import me.gorbuvla.map.screens.map.EventMapViewModel
import me.gorbuvla.map.model.FilterRepository
import me.gorbuvla.map.model.FilterRepositoryImpl
import me.gorbuvla.map.model.POIRepository
import me.gorbuvla.map.model.POIRepositoryImpl
import me.gorbuvla.map.model.filters.config.DefaultFilterSettings
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val featureModule = module {

    viewModel {
        EventMapViewModel(
            poiRepository = get(),
            filterRepository = get()
        )
    }

    viewModel {
        FiltersViewModel(repository = get())
    }

    single<POIRepository> {
        POIRepositoryImpl(
            eventRepository = get(),
            placeRepository = get()
        )
    }

    single<FilterRepository> {
        FilterRepositoryImpl(
            userSettings = get(),
            defaultSetting = get()
        )
    }

    single<SettingsConfig> {
        DefaultFilterSettings()
    }
}

val mapModule = domainModule + featureModule