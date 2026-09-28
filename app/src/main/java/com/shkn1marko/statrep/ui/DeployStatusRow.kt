package com.shkn1marko.statrep.ui

import androidx.compose.foundation.background
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.shkn1marko.statrep.model.DeployStatus
import com.shkn1marko.statrep.ui.theme.CardBackgroundColor
import com.shkn1marko.statrep.ui.theme.TimestampTextColor

@Composable
fun DeployStatusRow(status: DeployStatus) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(color = CardBackgroundColor, shape = RoundedCornerShape(6.dp))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = status.name,
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )

            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Tag(label = "Build", status = status.buildStatus)
                Tag(label = "Deploy", status = status.deployStatus)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = formatEpochSeconds(status.timestamp),
            color = TimestampTextColor,
            fontSize = 13.sp
        )
    }
}