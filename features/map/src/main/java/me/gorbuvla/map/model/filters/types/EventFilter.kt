package me.gorbuvla.map.model.filters.types

import me.gorbuvla.domain.domain.EventType
import me.gorbuvla.domain.util.implies
import me.gorbuvla.map.model.POI

/**
 * Filter for each individual [EventType].
 */
val EventType.filter: Filter<POI>
    get() = { poi, active -> (poi is POI.Event && poi.event.type == this) implies active }