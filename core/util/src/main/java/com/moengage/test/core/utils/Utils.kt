package com.moengage.test.core.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import com.moengage.test.core.utils.Constants.DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Utils {
    const val ZERO = 0
    const val ONE = 1
    const val TWO = 2
    const val THREE = 3
    const val FOUR = 4
    const val FIVE = 5
    const val SIX = 6
    const val SEVEN = 7
    const val EIGHT = 8
    const val TEN = 10

    private val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.US)

    /**
     * Opens the provided URL in the device's default web browser.
     * @param url The URL to be opened in the browser.
     */
    fun Activity.openUrlInBrowser(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        this.startActivity(browserIntent)
    }

    /**
     * Parses the provided date string into a Date object.
     * @param dateString The string representing the date.
     * @return The parsed Date object, or null if parsing fails.
     */
    fun parseDate(dateString: String?): Date? {
        try {
            return dateString?.let {
                dateFormat.parse(it)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * Extracts the first and last names from the provided string and passes them to the given action.
     * @param action A lambda function that takes two parameters: firstName and lastName.
     */
    fun String.populateCleanName(
        action: (firstName: String, lastName: String) -> Unit
    ) {
        if (this.isEmpty().not()) {
            val cleanedName = this.replace("//s+", "").trim()
            val names: List<String?> = cleanedName.split(" ")
            var firstName = ""
            var lastName = ""
            when (names.size) {
                THREE -> {
                    firstName = names[ZERO] ?: ""
                    lastName = names[TWO] ?: ""
                }

                TWO -> {
                    firstName = names[ZERO] ?: ""
                    lastName = names[ONE] ?: ""
                }

                ONE -> {
                    firstName = names[ZERO] ?: ""
                }
            }
            action.invoke(firstName, lastName)
        }
    }
}