package com.namma.travelcompanion.data.location

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.ServiceCompat
import androidx.core.content.ContextCompat


/**
 * Keeps GPS updates flowing while a trip runs with the screen off. It does no work itself: a running
 * location-type foreground service is what lets the app's FusedLocation request keep foreground-rate updates.
 */
class TripForegroundService : Service() {

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        TripNotifications.ensureChannels(this)
        val notification = NotificationCompat.Builder(this, TripNotifications.CHANNEL_TRIP)
            .setSmallIcon(android.R.drawable.ic_menu_mylocation)
            .setContentTitle("Trip in progress")
            .setContentText("Tracking distance and route for your ride")
            .setOngoing(true)
            .setContentIntent(TripNotifications.openAppIntent(this))
            .build()
        ServiceCompat.startForeground(this, NOTIFICATION_ID, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_LOCATION)
        return START_NOT_STICKY
    }

    companion object {
        private const val NOTIFICATION_ID = 41

        fun start(context: Context) =
            ContextCompat.startForegroundService(context, Intent(context, TripForegroundService::class.java))

        fun stop(context: Context) {
            context.stopService(Intent(context, TripForegroundService::class.java))
        }
    }
}

object TripNotifications {
    const val CHANNEL_TRIP = "trip_tracking"
    private const val CHANNEL_ALERT = "safety_alerts"
    private const val ALERT_ID = 42

    fun ensureChannels(context: Context) {
        val manager = context.getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(
            NotificationChannel(CHANNEL_TRIP, "Trip tracking", NotificationManager.IMPORTANCE_LOW)
        )
        manager.createNotificationChannel(
            NotificationChannel(CHANNEL_ALERT, "Safety alerts", NotificationManager.IMPORTANCE_HIGH).apply {
                enableVibration(true)
                vibrationPattern = longArrayOf(0, 500, 250, 500, 250, 500)
            }
        )
    }

    /** Heads-up, vibrating alert; works with the screen off. Silently skipped if notifications are blocked. */
    fun showSafetyAlert(context: Context, message: String) {
        ensureChannels(context)
        val notification: Notification = NotificationCompat.Builder(context, CHANNEL_ALERT)
            .setSmallIcon(android.R.drawable.stat_sys_warning)
            .setContentTitle("Ride is moving away from your drop point")
            .setContentText(message)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setAutoCancel(true)
            .setContentIntent(openAppIntent(context))
            .build()
        val manager = NotificationManagerCompat.from(context)
        if (manager.areNotificationsEnabled()) {
            try {
                manager.notify(ALERT_ID, notification)
            } catch (_: SecurityException) {
                // POST_NOTIFICATIONS revoked between the check and the call
            }
        }
    }

    fun openAppIntent(context: Context): PendingIntent = PendingIntent.getActivity(
        context,
        0,
        Intent(context, Class.forName("com.namma.travelcompanion.MainActivity")).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),
        PendingIntent.FLAG_IMMUTABLE
    )
}
