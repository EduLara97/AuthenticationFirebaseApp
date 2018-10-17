package com.example.itlab.authenticationfirebaseapp.notifications

import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.example.itlab.authenticationfirebaseapp.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.*

class NotificationService : FirebaseMessagingService() {
    private val DEMO_CHANNEL_ADMIN_ID = "com.example.itlab.authenticationfirebaseapp.channel_demo"
    private lateinit var notificationManager: NotificationManager

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.v("AuthFirebase", "Refreshed token: $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notificationId = Random().nextInt(60000) + (System.currentTimeMillis() and 0xfffffff).toInt()

        val title = remoteMessage.notification?.title
        val message = remoteMessage.notification?.body

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder = NotificationCompat.Builder(this, DEMO_CHANNEL_ADMIN_ID)
                .setSmallIcon(R.drawable.ic_notifications_active)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)

        notificationManager.notify(notificationId, notificationBuilder.build())
    }

}