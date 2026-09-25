package com.shkn1marko.statrep.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

import com.shkn1marko.statrep.R
import com.shkn1marko.statrep.model.DeployStatus

import java.util.concurrent.atomic.AtomicInteger

object DeployStatusNotifier {

    private const val CHANNEL_ID = "deploy_status_channel"
    private const val CHANNEL_NAME = "Deploy Status"

    private val notificationId = AtomicInteger(0)

    fun notify(context: Context, status: DeployStatus) {
        createChannelIfNeeded(context)

        if (ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.POST_NOTIFICATIONS
            ) != android.content.pm.PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_stat_notify)
            .setContentTitle(status.name)
            .setContentText("Build: ${status.buildStatus} / Deploy: ${status.deployStatus}")
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(context).notify(notificationId.getAndIncrement(), notification)
    }

    private fun createChannelIfNeeded(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = context.getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }
}