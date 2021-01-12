package me.gorbuvla.map.model.filters

import me.gorbuvla.domain.domain.EventType
import me.gorbuvla.map.model.filters.types.GeneralFilter
import me.gorbuvla.storage.Setting
import me.gorbuvla.ui.util.Text

/**
 * Filter option to be displayed in list of filters.
 */
sealed class FilterOption: Setting {

    data class General(val type: GeneralFilter, override val isActive: Boolean): FilterOption() {
        override val id: String
            get() = type.name

        override val title: Text
            get() = type.title
    }

    class Event(val type: EventType, override val isActive: Boolean): FilterOption() {
        override val id: String
            get() = type.name

        override val title: Text
            get() = Text.Raw(type.title)
    }

    abstract val isActive: Boolean

    abstract val title: Text
}