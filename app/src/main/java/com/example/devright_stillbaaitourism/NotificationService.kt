package com.example.devright_stillbaaitourism

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.Constants.TAG
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


/**
 * Constants for the notification channel ID and name.
 */
const val channelID = "notification_channel"
const val channelName = "com.example.devright_stillbaaitourism"

/**
 * Custom FirebaseMessagingService to handle incoming FCM messages.
 */
@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class NotificationService : FirebaseMessagingService() {

    /**
     * Overrides the onMessageReceived method to handle incoming FCM messages.
     * Generates a notification if the message contains a notification payload.
     */
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.getNotification() != null) {
            generateNotification(
                remoteMessage.notification!!.title!!,
                remoteMessage.notification!!.body!!
            )
        }
    }

    /**
     * Creates a RemoteViews object for the custom notification layout.
     *
     * @param title The title of the notification.
     * @param message The body text of the notification.
     * @return A RemoteViews object for the custom notification layout.
     */
    private fun getRemoteView(title: String, message: String): RemoteViews {
        val remoteView =
            RemoteViews("com.example.devright_stillbaaitourism", R.layout.custom_notification)
        remoteView.setTextViewText(R.id.tvNotificationTitle, title)
        remoteView.setTextViewText(R.id.tvNotificationMessage, message)
        remoteView.setImageViewResource(R.id.imgNotificationImage, R.drawable.app_logo)

        return remoteView
    }

    /**
     * Generates a notification with the specified title and message.
     *
     * @param title The title of the notification.
     * @param message The body text of the notification.
     */
    private fun generateNotification(title: String, message: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_ONE_SHOT
            )
        } else {
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        }

        //channel id, channel name
        var builder: NotificationCompat.Builder =
            NotificationCompat.Builder(applicationContext, channelID)
                .setSmallIcon(R.drawable.app_logo)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent)

        builder = builder.setContent(getRemoteView(title, message))

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)

        }
        notificationManager.notify(0, builder.build())
    }

    /**
     * Subscribes the device to the "Notification" topic using Firebase Cloud Messaging.
     * Logs the success or failure of the subscription.
     */
    public fun subscribeToNotifications() {
        FirebaseMessaging.getInstance().subscribeToTopic("Notification")
            .addOnCompleteListener { task ->
                var msg = "Subscribed"
                if (!task.isSuccessful) {
                    msg = "Subscribe failed"
                }
                Log.d(TAG, msg)
            }
    }

    /**
     * Unsubscribes the device from the "Notification" topic using Firebase Cloud Messaging.
     * Logs the success or failure of the unsubscription.
     */
    public fun unsubscribeFromNotifications() {
        FirebaseMessaging.getInstance().unsubscribeFromTopic("Notification")
            .addOnCompleteListener { task ->
                var msg = "Unsubscribed"
                if (!task.isSuccessful) {
                    msg = "Unsubscribe failed"
                }
                Log.d(TAG, msg)
            }
    }
}
// .........oooooooooo0000000000 END OF FILE 0000000000oooooooooo.......... //