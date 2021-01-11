package me.gorbuvla.storage

import android.content.Context

/**
 * Wrapper for various user settings.
 */
interface UserSettings {

    val filters: FilterSettings

    fun clear()
}

internal class UserSettingsImpl(context: Context, default: SettingsConfig) : UserSettings {

    private val sharedPreferences = context.getSharedPreferences("FILTERS", Context.MODE_PRIVATE)

    override val filters = FilterSettingsImpl(sharedPreferences, default)

    override fun clear() {
        sharedPreferences.edit()
            .clear()
            .apply()
    }
}
