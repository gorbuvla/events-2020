package me.gorbuvla.domain.model

import kotlinx.coroutines.delay
import me.gorbuvla.domain.domain.Place
import java.util.concurrent.TimeUnit

interface PlaceRepository {

    suspend fun places(): List<Place>
}

internal class PlaceRepositoryImpl() : PlaceRepository {

    override suspend fun places(): List<Place> {
        delay(TimeUnit.SECONDS.toMillis(1))
        return me.gorbuvla.domain.data.places
    }
}