package me.gorbuvla.ui.error

import androidx.annotation.StringRes

/**
 * Displayable types of errors/warnings.
 */
sealed class UIError {
    // Makes UIError extendable to support different handling than predefined types below.
    abstract class Custom: UIError()

    // Predefined [UIError] types
    data class Snackbar(@StringRes val textRes: Int): UIError()
    data class Dialog(@StringRes val titleRes: Int, @StringRes val bodyRes: Int): UIError()
}