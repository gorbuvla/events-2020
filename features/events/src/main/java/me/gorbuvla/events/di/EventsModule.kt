package me.gorbuvla.events.di

import me.gorbuvla.domain.di.domainModule
import me.gorbuvla.events.screens.detail.EventDetailViewModel
import me.gorbuvla.events.screens.list.EventListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {

    viewModel {
        EventListViewModel(repository = get())
    }

    viewModel { (eventId: String) ->
        EventDetailViewModel(
            eventId = eventId,
            repository = get()
        )
    }
}

val eventsModule = domainModule + featureModule