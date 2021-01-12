package me.gorbuvla.events.model

import me.gorbuvla.api.rest.services.EventService
import me.gorbuvla.domain.domain.Event

/**
 * TODO add class description
 */
interface EventRepository {

    suspend fun events(): List<Event>

    suspend fun event(id: String): Event

    suspend fun similar(eventId: String): List<Event>
}

internal class EventRepositoryImpl(private val eventService: EventService) : EventRepository {

    override suspend fun events(): List<Event> {
        return eventService.getEvents()
    }

    override suspend fun event(id: String): Event {
        return eventService.getEvent(id)
    }

    override suspend fun similar(eventId: String): List<Event> {
        return eventService.getSimilar(eventId)
    }
}