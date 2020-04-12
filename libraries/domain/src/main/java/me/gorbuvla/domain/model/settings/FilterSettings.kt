package me.gorbuvla.domain.model.settings

import android.content.SharedPreferences
import com.tfcporciuncula.flow.FlowSharedPreferences
import kotlinx.coroutines.flow.Flow

/**
 * Persistent user filter preferences.
 */
interface FilterSettings {

    fun collectChanges(filter: Setting): Flow<Boolean>

    fun isActive(filter: Setting): Boolean

    fun toggle(filter: Setting)

    fun set(filter: Setting, value: Boolean)
}

internal class FilterSettingsImpl(sharedPreferences: SharedPreferences, private val default: SettingsConfig) : FilterSettings {

    private val sharedPreferences = FlowSharedPreferences(sharedPreferences)

    companion object {

        private fun filterKey(filterId: String) = "filter-$filterId"
    }

    override fun collectChanges(filter: Setting): Flow<Boolean> {
        return sharedPreferences.getBoolean(filterKey(filter.id), default.isActiveByDefault(filter)).asFlow()
    }

    override fun isActive(filter: Setting): Boolean {
        return sharedPreferences.getBoolean(filterKey(filter.id), default.isActiveByDefault(filter)).get()
    }

    override fun toggle(filter: Setting) {
        with(sharedPreferences.getBoolean(filterKey(filter.id), default.isActiveByDefault(filter))) {
            set(get().not())
        }
    }

    override fun set(filter: Setting, value: Boolean) {
        sharedPreferences.getBoolean(filterKey(filter.id), default.isActiveByDefault(filter)).set(value)
    }
}