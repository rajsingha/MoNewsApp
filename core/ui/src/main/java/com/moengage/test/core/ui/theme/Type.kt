package com.moengage.test.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.moengage.test.core.ui.R

/**
 * Custom typography for the app, defining various text styles using the Montserrat font family.
 */

val robotoFont = FontFamily(
    Font(R.font.montserrat_light, weight = FontWeight.Normal),
    Font(R.font.montserrat_medium, weight = FontWeight.Medium),
    Font(R.font.montserrat_bold, weight = FontWeight.Bold),
    Font(R.font.montserrat_semibold, weight = FontWeight.SemiBold)
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontSize = text20,
        lineHeight = text28,
        fontFamily = robotoFont,
        fontWeight = FontWeight.Medium
    ),
    displayMedium = TextStyle(
        fontSize = text18,
        lineHeight = text26,
        fontFamily = robotoFont,
        fontWeight = FontWeight.Medium
    ),
    displaySmall = TextStyle(
        fontSize = text16,
        lineHeight = text24,
        fontFamily = robotoFont,
        fontWeight = FontWeight.SemiBold
    ),
    headlineLarge = TextStyle(
        fontSize = text14,
        lineHeight = text22,
        fontFamily = robotoFont,
        fontWeight = FontWeight.Medium
    ),
    headlineMedium = TextStyle(
        fontSize = text12,
        lineHeight = text20,
        fontFamily = robotoFont,
        fontWeight = FontWeight.Medium
    ),
    headlineSmall = TextStyle(
        fontSize = text11,
        lineHeight = text20,
        fontFamily = robotoFont,
        fontWeight = FontWeight.Medium
    ),
    titleLarge = TextStyle(
        fontSize = text16,
        lineHeight = text22,
        fontFamily = robotoFont,
        fontWeight = FontWeight.Normal
    ),
    titleMedium = TextStyle(
        fontSize = text14,
        lineHeight = text20,
        fontFamily = robotoFont,
        fontWeight = FontWeight.Normal
    ),
    titleSmall = TextStyle(
        fontSize = text12,
        lineHeight = text18,
        fontFamily = robotoFont,
        fontWeight = FontWeight.Normal
    )
)