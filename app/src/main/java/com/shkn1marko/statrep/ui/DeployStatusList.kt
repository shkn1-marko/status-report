package com.shkn1marko.statrep.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.shkn1marko.statrep.model.DeployStatus

@Composable
fun DeployStatusList(statuses: List<DeployStatus>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 32.dp)
    ) {
        items(statuses) { status ->
            DeployStatusRow(status = status)
        }
    }
}