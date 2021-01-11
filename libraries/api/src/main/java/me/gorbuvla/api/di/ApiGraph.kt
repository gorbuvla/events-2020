package me.gorbuvla.api.di

import me.gorbuvla.api.rest.services.EventService
import me.gorbuvla.api.rest.services.MockEventService
import me.gorbuvla.api.rest.services.MockPlaceService
import me.gorbuvla.api.rest.services.PlaceService
import org.koin.dsl.module

object ApiGraph {

    private val serviceModule = module {

        single<EventService> {
            MockEventService()
        }

        single<PlaceService> {
            MockPlaceService()
        }
    }

    val modules = serviceModule
}