package me.gorbuvla.map.model.filters.types

import me.gorbuvla.domain.util.implies
import me.gorbuvla.map.model.POI
import me.gorbuvla.ui.util.Text

/**
 * Enumeration of all possible general filters.
 */
enum class GeneralFilter(val title: Text, val filter: Filter<POI>) {
    PLACES(Text.Raw("Show Places"), { poi, active -> (poi is POI.Stop) implies active }),
}
