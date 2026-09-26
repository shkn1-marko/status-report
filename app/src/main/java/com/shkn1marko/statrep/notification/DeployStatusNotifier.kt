package com.shkn1marko.statrep.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.annotation.SuppressLint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

import com.shkn1marko.statrep.R
import com.shkn1marko.statrep.model.DeployStatus
import com.shkn1marko.statrep.MainActivity

import java.util.concurrent.atomic.AtomicInteger

object DeployStatusNotifier {

    private const val CHANNEL_ID = "deploy_status_channel"
    private const val CHANNEL_NAME = "Deploy Status"

    private val notificationId = AtomicInteger(0)

    private data class NotificationContent(
        val color: Color,
        val title: String,
        val text: String
    )

    private fun buildNotificationContent(status: DeployStatus): NotificationContent =
        if (status.isSuccess) {
            NotificationContent(
                color = Color.Green,
                title = "OK : ${status.name}",
                text = "All systems go - nothing needs your attention."
            )
        } else {
            NotificationContent(
                color = Color.Red,
                title = "ERR : ${status.name}",
                text = "Heads up - something went wrong. Tap for details."
            )
        }

    private fun buildContentIntent(context: Context): PendingIntent {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        return PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun hasNotificationPermission(context: Context): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) return true

        return ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.POST_NOTIFICATIONS
        ) == android.content.pm.PackageManager.PERMISSION_GRANTED
    }

    @SuppressLint("MissingPermission")
    fun notify(context: Context, status: DeployStatus) {
        createChannelIfNeeded(context)

        if (!hasNotificationPermission(context)) {
            return
        }

        val content = buildNotificationContent(status)
        val pendingIntent = buildContentIntent(context)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_stat_notify)
            .setColor(content.color.toArgb())
            .setContentTitle(content.title)
            .setContentText(content.text)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
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