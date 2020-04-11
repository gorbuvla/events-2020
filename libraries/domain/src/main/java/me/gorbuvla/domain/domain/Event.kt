package me.gorbuvla.domain.domain

/**
 * TODO add class description
 */
data class Event(
    val id: String,
    val title: String,
    val description: String,
    val image: String,
    val type: EventType,
    val location: Coordinate
)

enum class EventType {

    CONFERENCE, TRAVEL, CONCERT, MOVIE
}

