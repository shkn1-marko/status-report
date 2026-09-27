package com.shkn1marko.statrep.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

import com.shkn1marko.statrep.R

val InterFontFamily = FontFamily(
    Font(R.font.inter_regular18, FontWeight.Normal),
    Font(R.font.inter_semibold18, FontWeight.SemiBold)
)

val Typography = Typography().let { defaults ->
    Typography(
        displayLarge = defaults.displayLarge.copy(fontFamily = InterFontFamily),
        displayMedium = defaults.displayMedium.copy(fontFamily = InterFontFamily),
        displaySmall = defaults.displaySmall.copy(fontFamily = InterFontFamily),
        headlineLarge = defaults.headlineLarge.copy(fontFamily = InterFontFamily),
        headlineMedium = defaults.headlineMedium.copy(fontFamily = InterFontFamily),
        headlineSmall = defaults.headlineSmall.copy(fontFamily = InterFontFamily),
        titleLarge = defaults.titleLarge.copy(fontFamily = InterFontFamily),
        titleMedium = defaults.titleMedium.copy(fontFamily = InterFontFamily),
        titleSmall = defaults.titleSmall.copy(fontFamily = InterFontFamily),
        bodyLarge = defaults.bodyLarge.copy(fontFamily = InterFontFamily),
        bodyMedium = defaults.bodyMedium.copy(fontFamily = InterFontFamily),
        bodySmall = defaults.bodySmall.copy(fontFamily = InterFontFamily),
        labelLarge = defaults.labelLarge.copy(fontFamily = InterFontFamily),
        labelMedium = defaults.labelMedium.copy(fontFamily = InterFontFamily),
        labelSmall = defaults.labelSmall.copy(fontFamily = InterFontFamily)
    )
}