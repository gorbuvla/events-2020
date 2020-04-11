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
}

internal class EventRepositoryImpl : EventRepository {

    override suspend fun events(): List<Event> {
        delay(TimeUnit.SECONDS.toMillis(2))
        return conferences + concerts + travel + movies
    }
}