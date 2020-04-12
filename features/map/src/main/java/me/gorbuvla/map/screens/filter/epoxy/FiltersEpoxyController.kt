package me.gorbuvla.map.screens.filter.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import me.gorbuvla.map.screens.filter.FilterConfiguration
import me.gorbuvla.map.model.filters.FilterOption
import me.gorbuvla.ui.epoxy.section
import me.gorbuvla.ui.epoxy.spacer

class FiltersEpoxyController(
    private val onToggle: (FilterOption) -> Unit
): TypedEpoxyController<FilterConfiguration>() {

    override fun buildModels(configuration: FilterConfiguration) {
        configuration.sections.forEach { section ->
            section {
                id(section.id)
                title(section.name)
            }

            section.filters.forEach { filter ->
                filterOption {
                    id(filter.id)
                    filter(filter)
                    onToggle(onToggle)
                }
            }

            spacer {
                id("spacer-after-${section.id}")
            }
        }
    }
}