package me.gorbuvla.api.rest.services

import kotlinx.coroutines.delay
import me.gorbuvla.domain.data.places
import me.gorbuvla.domain.domain.Place
import java.util.concurrent.TimeUnit

/**
 * Remote data source
 */
interface PlaceService {

    suspend fun getPlaces(): List<Place>
}

internal class MockPlaceService : PlaceService {

    override suspend fun getPlaces(): List<Place> {
        delay(TimeUnit.SECONDS.toMillis(1))
        return places
    }
}