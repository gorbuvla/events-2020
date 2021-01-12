package me.gorbuvla.storage

/**
 * Interface to provide default config for settings
 */
interface SettingsConfig {
    fun isActiveByDefault(setting: Setting): Boolean
}