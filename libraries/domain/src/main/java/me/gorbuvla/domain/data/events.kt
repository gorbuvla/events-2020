package me.gorbuvla.domain.data

import me.gorbuvla.domain.domain.Coordinate
import me.gorbuvla.domain.domain.Event
import me.gorbuvla.domain.domain.EventType

/**
 * TODO add class description
 */
val events = listOf(

    Event(
        id = "google-io",
        title = "Google IO",
        description = "Important",
        type = EventType.CONFERENCE,
        location = Coordinate(37.4043677,-122.162594)
    )
)