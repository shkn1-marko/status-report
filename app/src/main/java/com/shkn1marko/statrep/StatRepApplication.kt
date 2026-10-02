package com.shkn1marko.statrep

import android.app.Application
import com.google.firebase.messaging.FirebaseMessaging

class StatRepApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseMessaging.getInstance().register()
    }
}