package me.gorbuvla.events.di

import me.gorbuvla.domain.di.domainModule
import me.gorbuvla.events.flow.list.EventListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {

    viewModel {
        EventListViewModel(repository = get())
    }
}

val eventsModule = domainModule + featureModule