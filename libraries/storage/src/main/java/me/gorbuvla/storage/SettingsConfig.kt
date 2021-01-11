package me.gorbuvla.storage

/**
 * Interface to provide default config for settings
 */
internal interface SettingsConfig {
    fun isActiveByDefault(setting: Setting): Boolean
}