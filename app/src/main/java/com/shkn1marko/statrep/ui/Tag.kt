package com.shkn1marko.statrep.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.shkn1marko.statrep.model.StageStatus
import com.shkn1marko.statrep.ui.theme.TagFailureBackground
import com.shkn1marko.statrep.ui.theme.TagFailureBorder
import com.shkn1marko.statrep.ui.theme.TagFailureText
import com.shkn1marko.statrep.ui.theme.TagSkippedBackground
import com.shkn1marko.statrep.ui.theme.TagSkippedBorder
import com.shkn1marko.statrep.ui.theme.TagSkippedText
import com.shkn1marko.statrep.ui.theme.TagSuccessBackground
import com.shkn1marko.statrep.ui.theme.TagSuccessBorder
import com.shkn1marko.statrep.ui.theme.TagSuccessText

@Composable
fun Tag(label: String, status: StageStatus) {
    val (backgroundColor, borderColor, textColor) = when (status) {
        StageStatus.OK -> Triple(TagSuccessBackground, TagSuccessBorder, TagSuccessText)
        StageStatus.FAILED -> Triple(TagFailureBackground, TagFailureBorder, TagFailureText)
        StageStatus.SKIPPED -> Triple(TagSkippedBackground, TagSkippedBorder, TagSkippedText)
    }

    Text(
        text = label,
        modifier = Modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(8.dp))
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        color = textColor,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center
    )
}