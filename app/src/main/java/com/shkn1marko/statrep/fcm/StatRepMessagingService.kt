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

class StatRepMessagingService : FirebaseMessagingService() {

    private val repository by lazy {
        DeployStatusRepository(StatRepDatabase.getInstance(this).deployStatusDao())
    }

    override fun onRegistered(installationId: String) {
        super.onRegistered(installationId)
        Log.d(TAG, "FCM installation ID: $installationId")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val status = DeployStatus.fromData(message.data)
        if (status != null) {
            Log.d(TAG, "Parsed DeployStatus: $status")
            CoroutineScope(Dispatchers.IO).launch {
                repository.insert(status)
            }
        } else {
            Log.w(TAG, "Failed to parse DeployStatus from data: ${message.data}")
        }
    }

    companion object {
        private const val TAG = "StatRepMessaging"
    }
}