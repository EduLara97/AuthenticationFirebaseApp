package com.example.itlab.authenticationfirebaseapp.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.example.itlab.authenticationfirebaseapp.BuildConfig
import com.example.itlab.authenticationfirebaseapp.R
import com.example.itlab.authenticationfirebaseapp.shared.Constants
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.*

class NotificationService : FirebaseMessagingService() {

    private var notificationManager: NotificationManager? = null

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e(Constants.TAG, "Refreshed token: $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            setupChannels()
        }

        val notificationId = Random().nextInt(60000) + (System.currentTimeMillis() and 0xfffffff).toInt()

        //val title = remoteMessage.data["title"]
        //val message = remoteMessage.data["message"]

        val title = remoteMessage.notification?.title
        val message = remoteMessage.notification?.body

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder = NotificationCompat.Builder(this, BuildConfig.DEMO_CHANNEL_ADMIN_ID)
                .setSmallIcon(R.drawable.ic_notifications_active)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)

        if (notificationManager != null) {
            notificationManager!!.notify(notificationId, notificationBuilder.build())
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun setupChannels() {
        val adminChannelName = getString(R.string.notifications_admin_channel_name)
        val adminChannelDescription = getString(R.string.notifications_admin_channel_description)
        val adminChannel: NotificationChannel

        adminChannel = NotificationChannel(BuildConfig.DEMO_CHANNEL_ADMIN_ID, adminChannelName, NotificationManager.IMPORTANCE_LOW)
        adminChannel.description = adminChannelDescription
        adminChannel.enableLights(true)
        adminChannel.lightColor = Color.RED
        adminChannel.enableVibration(true)

        if (notificationManager != null) {
            notificationManager!!.createNotificationChannel(adminChannel)
        }
    }

}