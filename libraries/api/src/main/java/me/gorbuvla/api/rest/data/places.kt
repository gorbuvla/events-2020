package me.gorbuvla.domain.data

import me.gorbuvla.domain.domain.Address
import me.gorbuvla.domain.domain.Coordinate
import me.gorbuvla.domain.domain.Place

// Static data

val places = listOf(
    Place(
        name = "AquaDom & SEA LIFE",
        address = Address(
            name = "Berlin",
            location = Coordinate(52.5201544,13.4036905)
        )
    )
)