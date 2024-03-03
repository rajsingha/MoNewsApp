package com.moengage.test.newsapp.core.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import dagger.hilt.android.HiltAndroidApp

/**
 * The [NewsApp] class represents the custom application class for the News App.
 * It initializes necessary components when the application starts.
 */
@HiltAndroidApp
class NewsApp : Application() {

    /**
     * Called when the application is starting. This is called before any other application components
     * are created.
     */
    override fun onCreate() {
        super.onCreate()
        setupNotificationChannel()
    }

    /**
     * Sets up the notification channel for the application. This is required for Android
     * versions Oreo (API level 26) and above.
     */
    private fun setupNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    companion object {
        // Constants for notification channel
        private const val CHANNEL_ID = "monews_app"
        private const val CHANNEL_NAME = "monews_notfy_channel"
    }
}