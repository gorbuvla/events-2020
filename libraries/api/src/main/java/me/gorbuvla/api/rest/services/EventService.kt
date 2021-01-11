package me.gorbuvla.api.rest.services

import kotlinx.coroutines.delay
import me.gorbuvla.domain.data.concerts
import me.gorbuvla.domain.data.conferences
import me.gorbuvla.domain.data.movies
import me.gorbuvla.domain.data.travel
import me.gorbuvla.domain.domain.Event
import java.util.concurrent.TimeUnit

/**
 * Remote data source for [Event]
 */
interface EventService {

    suspend fun getEvents(): List<Event>

    suspend fun getEvent(id: String): Event

    suspend fun getSimilar(eventId: String): List<Event>
}

internal class MockEventService : EventService {

    private val events = conferences + concerts + travel + movies

    override suspend fun getEvents(): List<Event> {
        delay(TimeUnit.SECONDS.toMillis(2))
        return events
    }

    override suspend fun getEvent(id: String): Event {
        delay(TimeUnit.SECONDS.toMillis(2))
        return events.find { it.id == id } ?: throw IllegalStateException("No event found with id: $id")
    }

    override suspend fun getSimilar(eventId: String): List<Event> {
        delay(TimeUnit.SECONDS.toMillis(1))
        val event = events.find { it.id == eventId } ?: return emptyList()
        return events.filter { it.type == event.type && it.id != event.id }
    }
}