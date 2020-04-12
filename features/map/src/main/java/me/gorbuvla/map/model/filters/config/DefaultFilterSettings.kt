package me.gorbuvla.map.model.filters.config

import me.gorbuvla.domain.model.settings.Setting
import me.gorbuvla.domain.model.settings.SettingsConfig
import me.gorbuvla.map.model.filters.types.GeneralFilter

class DefaultFilterSettings: SettingsConfig {

    override fun isActiveByDefault(setting: Setting): Boolean {
        return setting.id != GeneralFilter.PLACES.name
    }
}