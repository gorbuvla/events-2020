package me.gorbuvla.map.model.filters

import me.gorbuvla.ui.util.Text

/**
 * Represents a filter section which has a title
 */
data class FilterSection(
    val name: Text,
    val filters: List<FilterOption>
) {

    val id: String = "section" + filters.joinToString { it.id }
}