package me.gorbuvla.domain.model

import kotlinx.coroutines.delay
import me.gorbuvla.domain.data.concerts
import me.gorbuvla.domain.data.conferences
import me.gorbuvla.domain.data.movies
import me.gorbuvla.domain.data.travel
import me.gorbuvla.domain.domain.Event
import java.util.concurrent.TimeUnit

/**
 * TODO add class description
 */
interface EventRepository {

    suspend fun events(): List<Event>

    suspend fun event(id: String): Event

    suspend fun similar(eventId: String): List<Event>
}

internal class EventRepositoryImpl : EventRepository {

    private val events = conferences + concerts + travel + movies

    override suspend fun events(): List<Event> {
        delay(TimeUnit.SECONDS.toMillis(2))
        return events
    }

    override suspend fun event(id: String): Event {
        delay(TimeUnit.SECONDS.toMillis(1))
        return events.first { it.id == id }
    }

    override suspend fun similar(eventId: String): List<Event> {
        delay(TimeUnit.SECONDS.toMillis(1))
        val event = events.first { it.id == eventId }
        return events.filter { it.type == event.type && it.id != event.id }
    }
}