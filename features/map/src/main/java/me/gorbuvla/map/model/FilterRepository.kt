package me.gorbuvla.map.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import me.gorbuvla.domain.domain.EventType
import me.gorbuvla.domain.model.settings.Setting
import me.gorbuvla.domain.model.settings.SettingsConfig
import me.gorbuvla.domain.model.settings.UserSettings
import me.gorbuvla.map.model.filters.FilterOption
import me.gorbuvla.map.model.filters.types.FilterApplicator
import me.gorbuvla.map.model.filters.types.GeneralFilter
import me.gorbuvla.map.model.filters.types.filter

/**
 * Repository to manage map filters.
 */
interface FilterRepository {

    val filters: Flow<Filters>

    val defaultFiltersChanged: Flow<Boolean>

    fun isActive(filter: FilterOption): Boolean

    fun toggle(filter: FilterOption)
}

class FilterRepositoryImpl(
    private val userSettings: UserSettings,
    private val defaultSetting: SettingsConfig
): FilterRepository {

    override val filters: Flow<Filters>
        get() {
            val general = GeneralFilter.values().map {
                userSettings.filters.collectChanges(it.asSetting()).map { isActive -> FilterOption.General(it, isActive) }
            }
            val events = EventType.values().map {
                userSettings.filters.collectChanges(it.asSetting()).map { isActive -> FilterOption.Event(it, isActive) }
            }

            return combine(general + events) { filters ->
                Filters(
                    general = filters.filterIsInstance<FilterOption.General>(),
                    events = filters.filterIsInstance<FilterOption.Event>()
                )
            }
        }

    override val defaultFiltersChanged: Flow<Boolean>
        get() = filters.map { (general, events) ->
            val generalChanged = general.fold(true) { acc, filter -> acc && (filter.isActive == defaultSetting.isActiveByDefault(filter.type.asSetting())) }
            val eventsChanged = events.fold(true) { acc, filter -> acc && (filter.isActive == defaultSetting.isActiveByDefault(filter.type.asSetting())) }

            generalChanged && eventsChanged
        }


    override fun isActive(filter: FilterOption): Boolean {
        return userSettings.filters.isActive(filter)
    }

    override fun toggle(filter: FilterOption) {
        userSettings.filters.toggle(filter)
    }

    private data class SettingConvertible(override val id: String): Setting

    private fun GeneralFilter.asSetting(): Setting = SettingConvertible(name)
    private fun EventType.asSetting(): Setting = SettingConvertible(name)
}

data class Filters(
    val general: List<FilterOption.General>,
    val events: List<FilterOption.Event>
) {

    fun apply(data: List<POI>): List<POI> {
        val generalApplicators = general.map { FilterApplicator(it.isActive, it.type.filter) }
        val eventApplicators = events.map { FilterApplicator(it.isActive, it.type.filter) }
        return data
            .filter { poi -> eventApplicators.fold(true) { acc, applicator -> acc && applicator(poi) } }
            .filter { poi -> generalApplicators.fold(true) { acc, applicator -> acc && applicator(poi) } }
    }
}
