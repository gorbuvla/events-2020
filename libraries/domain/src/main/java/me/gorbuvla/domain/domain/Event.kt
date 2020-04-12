package me.gorbuvla.domain.domain

data class Event(
    val id: String,
    val title: String,
    val description: String,
    val image: String,
    val type: EventType,
    val address: Address,
    val url: String
)

data class Address(
    val name: String,
    val location: Coordinate
)

enum class EventType(val title: String) {

    CONFERENCE("Conferences"), TRAVEL("Flex & Chill"), CONCERT("Concerts"), MOVIE("Movies")
}