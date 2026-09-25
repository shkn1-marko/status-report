package com.shkn1marko.statrep.fcm

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import com.shkn1marko.statrep.db.DeployStatusRepository
import com.shkn1marko.statrep.db.StatRepDatabase
import com.shkn1marko.statrep.model.DeployStatus
import com.shkn1marko.statrep.notification.AppLifecycleObserver
import com.shkn1marko.statrep.notification.DeployStatusNotifier

class StatRepMessagingService : FirebaseMessagingService() {

    private val repository by lazy {
        DeployStatusRepository(StatRepDatabase.getInstance(this).deployStatusDao())
    }

    override fun onRegistered(installationId: String) {
        super.onRegistered(installationId)
        Log.d("StatRepMessaging", "FCM installation ID: $installationId")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val status = DeployStatus.fromData(message.data)
        if (status != null) {
            CoroutineScope(Dispatchers.IO).launch {
                repository.insert(status)
            }

            if (!AppLifecycleObserver.isInForeground) {
                DeployStatusNotifier.notify(this, status)
            }
        }
    }
}