package com.shkn1marko.statrep.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import com.shkn1marko.statrep.model.DeployStatus

@Composable
fun DeployStatusRow(status: DeployStatus) {
    Text("${status.name} - ${status.buildStatus} / ${status.deployStatus}")
}