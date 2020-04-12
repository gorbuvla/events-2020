package me.gorbuvla.ui.util

import android.content.Context
import androidx.annotation.DimenRes

/**
 * Representation of dimension to be used in viewModels & epoxy models.
 */
sealed class Dimen {
    data class Dp(val size: Int): Dimen() {

        //returns dip(dp) dimension value in pixels
        private fun Context.dip(value: Int): Int = (value * resources.displayMetrics.density).toInt()

        override fun resolve(context: Context): Int = if (size != 0) context.dip(size) else 0
    }

    data class Res(@DimenRes val dimenRes: Int): Dimen() {
        override fun resolve(context: Context): Int = context.resources.getDimension(dimenRes).toInt()
    }

    abstract fun resolve(context: Context): Int
}

