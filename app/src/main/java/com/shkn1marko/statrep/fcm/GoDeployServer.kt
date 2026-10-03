package com.shkn1marko.statrep.fcm

import java.net.HttpURLConnection
import java.net.URL
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

import com.shkn1marko.statrep.BuildConfig

object GoDeployServer {

    fun registerDevice(installationId: String) {
        try {
            postRegistration(installationId)
        } catch (e: Exception) { }
    }

    private fun postRegistration(installationId: String) {
        val body = """{"installationID":"$installationId"}"""
        val signature = hmacSha256Hex(BuildConfig.GODEPLOY_REGISTER_SECRET, body)

        val connection = URL("${BuildConfig.GODEPLOY_BASE_URL}/register-device")
            .openConnection() as HttpURLConnection
        try {
            connection.requestMethod = "POST"
            connection.doOutput = true
            connection.setRequestProperty("Content-Type", "application/json")
            connection.setRequestProperty("X-GDEP-Signature-256", "sha256=$signature")

            connection.outputStream.use { it.write(body.toByteArray()) }
            connection.responseCode // force the request to complete
        } finally {
            connection.disconnect()
        }
    }

    private fun hmacSha256Hex(secret: String, message: String): String {
        val mac = Mac.getInstance("HmacSHA256")
        mac.init(SecretKeySpec(secret.toByteArray(), "HmacSHA256"))
        return mac.doFinal(message.toByteArray()).joinToString("") { "%02x".format(it) }
    }
}