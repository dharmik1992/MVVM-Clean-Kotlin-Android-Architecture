package com.mvvm.clean.app.presentation.utils

import androidx.annotation.StringRes
import com.mvvm.clean.app.R
import java.net.UnknownHostException

/**
 * File which returns messages based on exception received
 */
internal object ExceptionHandler {

    /**
     * Function returns user friendly message based on the exception type
     *
     * @param t Exception object
     * @return String type of message
     */
    @StringRes
    fun parse(t: Throwable): Int {
        return when (t) {
            is UnknownHostException -> R.string.error_check_internet_connection
            else -> R.string.error_oops_error_occured
        }
    }
}