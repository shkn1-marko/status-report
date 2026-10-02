package com.shkn1marko.statrep

import android.app.Application
import com.google.firebase.messaging.FirebaseMessaging
import com.shkn1marko.statrep.notification.AppLifecycleObserver

class StatRepApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppLifecycleObserver.init()
        FirebaseMessaging.getInstance().register()
    }
}