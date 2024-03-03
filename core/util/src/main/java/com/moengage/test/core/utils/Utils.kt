package com.moengage.test.core.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import com.moengage.test.core.utils.Constants.DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Utils {
    private val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.US)
    fun Activity.openUrlInBrowser(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        this.startActivity(browserIntent)
    }

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
}