package me.gorbuvla.domain.model.settings

import android.content.SharedPreferences

/**
 * Wrapper for various user settings.
 */
interface UserSettings {

    val filters: FilterSettings

    fun clear()
}

internal class UserSettingsImpl(
    private val sharedPreferences: SharedPreferences,
    default: SettingsConfig
) : UserSettings {

    override val filters = FilterSettingsImpl(sharedPreferences, default)

    override fun clear() {
        sharedPreferences.edit()
            .clear()
            .apply()
    }
}