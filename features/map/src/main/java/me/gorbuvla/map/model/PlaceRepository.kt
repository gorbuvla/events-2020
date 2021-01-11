package me.gorbuvla.map.model

import me.gorbuvla.api.rest.services.PlaceService
import me.gorbuvla.domain.domain.Place

interface PlaceRepository {

    suspend fun places(): List<Place>
}

internal class PlaceRepositoryImpl(private val placeService: PlaceService) : PlaceRepository {

    override suspend fun places(): List<Place> {
        return placeService.getPlaces()
    }
}