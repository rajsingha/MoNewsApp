package com.moengage.test.newsapp.core.notificationservices

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 * Service for handling Firebase Cloud Messaging (FCM) events.
 */
class FCMService : FirebaseMessagingService() {

    /**
     * Called when a new FCM message is received.
     * @param message The received message containing data from FCM.
     */
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        // Custom handling of received message can be implemented here
    }

    /**
     * Called when a new FCM token is generated or refreshed.
     * @param token The new FCM token.
     */
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // Handle token refresh or registration here
    }
}