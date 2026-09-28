package com.shkn1marko.statrep.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.shkn1marko.statrep.model.DeployStatus
import com.shkn1marko.statrep.ui.theme.CardBackgroundColor
import com.shkn1marko.statrep.ui.theme.TimestampTextColor

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DeployStatusRow(status: DeployStatus) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(color = CardBackgroundColor, shape = RoundedCornerShape(6.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { expanded = !expanded }
            .padding(16.dp)
    ) {
        CardTitleAndStatus(status = status)
        Spacer(modifier = Modifier.height(32.dp))

        CardTimestamp(status = status)
        Spacer(modifier = Modifier.height(8.dp))

        AnimatedVisibility(visible = expanded && status.hasDetails) {
            CardDetails(status)
        }
    }
}

@Composable
private fun CardTitleAndStatus(status: DeployStatus) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CardTitle(status = status, modifier = Modifier.weight(1f))
        CardStatus(status = status)
    }
}

@Composable
private fun CardTitle(status: DeployStatus, modifier: Modifier) {
    Text(
        text = status.name,
        modifier = modifier,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        overflow = TextOverflow.Ellipsis,
        maxLines = 2
    )
}

@Composable
private fun CardStatus(status: DeployStatus) {
    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        Tag(label = "Build", status = status.buildStatus)
        Tag(label = "Deploy", status = status.deployStatus)
    }
}

@Composable
private fun CardTimestamp(status: DeployStatus) {
    val formatter = SimpleDateFormat("MMM d, yyyy 'at' HH:mm", Locale.getDefault())
    val timestamp = formatter.format(Date(status.timestamp * 1000))

    Text(
        text = timestamp,
        color = TimestampTextColor,
        fontSize = 13.sp
    )
}

@Composable
private fun CardDetails(status: DeployStatus) {
    Column {
        status.cause?.let { cause -> CardDetail(label = "Cause", value = cause) }
        status.output?.let { output -> CardDetail(label = "Output", value = output) }
    }
}

@Composable
private fun CardDetail(label: String, value: String) {
    Column {
        Text(text = label, fontWeight = FontWeight.SemiBold)
        Text(text = value, modifier = Modifier.padding(4.dp))
    }
}