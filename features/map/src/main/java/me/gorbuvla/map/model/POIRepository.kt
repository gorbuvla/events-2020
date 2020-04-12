package me.gorbuvla.map.model

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.gorbuvla.domain.domain.Place
import me.gorbuvla.domain.model.EventRepository
import me.gorbuvla.domain.model.PlaceRepository
import me.gorbuvla.domain.domain.Event as DomainEvent

interface POIRepository {

    val poi: Flow<List<POI>>
}

internal class POIRepositoryImpl(
    private val eventRepository: EventRepository,
    private val placeRepository: PlaceRepository
): POIRepository {

    override val poi: Flow<List<POI>>
        get() = flow { emit(fetchPOI()) }

    private suspend fun fetchPOI(): List<POI> {
        return coroutineScope {
            val events = async { eventRepository.events().map { POI.Event(it) } }
            val places = async { placeRepository.places().map { POI.Stop(it) } }

            events.await() + places.await()
        }
    }
}

sealed class POI {

    data class Event(val event: DomainEvent) : POI()
    data class Stop(val place: Place) : POI()
}