package me.gorbuvla.ui.util

import android.content.Context
import androidx.annotation.StringRes

/**
 * Representation of text to be used in viewModels & epoxy models.
 */
sealed class Text {

    data class Raw(val text: String): Text() {
        override fun resolve(context: Context): String = text
    }
    data class Resource(@StringRes val resourceId: Int): Text() {
        override fun resolve(context: Context): String = context.getString(resourceId)
    }

    abstract fun resolve(context: Context): String
}