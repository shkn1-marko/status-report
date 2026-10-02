package com.shkn1marko.statrep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.app.NotificationManagerCompat

import com.shkn1marko.statrep.ui.theme.StatRepTheme
import com.shkn1marko.statrep.ui.StatRepViewModel
import com.shkn1marko.statrep.ui.DeployStatusList

class MainActivity : ComponentActivity() {

    private val viewModel: StatRepViewModel by viewModels()

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        askNotificationPermission(requestPermissionLauncher)
        enableEdgeToEdge()
        setContent {
            StatRepTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val deployStatuses by viewModel.deployStatuses.collectAsState()
                    DeployStatusList(
                        statuses = deployStatuses,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.deleteExpired()
        NotificationManagerCompat.from(this).cancelAll()
    }
}