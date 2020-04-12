package me.gorbuvla.ui.util

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.IntRange
import cz.ackee.extensions.android.colorWithOpacity

/**
 * Representation of color to be used in viewModels & epoxy models.
 */
sealed class Color {
    data class Resolved(@ColorInt val colorInt: Int): Color() {
        override fun resolve(context: Context): Int = colorInt
    }
    data class Resource(@ColorRes val colorRes: Int, @IntRange(from = 0, to = 100) val opacity: Int = 100): Color() {
        override fun resolve(context: Context): Int = context.colorWithOpacity(colorRes, opacity)
    }

    @ColorInt
    abstract fun resolve(context: Context): Int
}