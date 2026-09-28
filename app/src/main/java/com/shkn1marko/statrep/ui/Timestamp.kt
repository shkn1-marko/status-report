package com.shkn1marko.statrep.ui

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatEpochSeconds(epochSeconds: Long): String {
    val formatter = SimpleDateFormat("MMM d, yyyy 'at' HH:mm", Locale.getDefault())
    return formatter.format(Date(epochSeconds * 1000))
}