package me.gorbuvla.domain.model.settings

/**
 * Interface to provide default config for settings
 */
interface SettingsConfig {
    fun isActiveByDefault(setting: Setting): Boolean
}