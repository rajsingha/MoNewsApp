package com.moengage.test.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


/**
 * [Spacing] Data class containing different Spacing Values
 * */
data class spacing(
    val default: Dp = 0.dp,
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 32.dp,
    val extraLarge: Dp = 64.dp,
    val spacing05: Dp = 0.5.dp,
    val spacing1: Dp = 1.dp,
    val spacing2: Dp = 2.dp,
    val spacing3: Dp = 3.dp,
    val spacing4: Dp = 4.dp,
    val spacing5: Dp = 5.dp,
    val spacing6: Dp = 6.dp,
    val spacing8: Dp = 8.dp,
    val spacing10: Dp = 10.dp,
    val spacing12: Dp = 12.dp,
    val spacing14: Dp = 14.dp,
    val spacing15: Dp = 15.dp,
    val spacing16: Dp = 16.dp,
    val spacing18: Dp = 18.dp,
    val spacing20: Dp = 20.dp,
    val spacing24: Dp = 24.dp,
    val spacing25: Dp = 25.dp,
    val spacing26: Dp = 26.dp,
    val spacing28: Dp = 28.dp,
    val spacing30: Dp = 30.dp,
    val spacing32: Dp = 32.dp,
    val spacing35: Dp = 35.dp,
    val spacing38: Dp = 38.dp,
    val spacing40: Dp = 40.dp,
    val spacing44: Dp = 44.dp,
    val spacing48: Dp = 48.dp,
    val spacing50: Dp = 50.dp,
    val spacing60: Dp = 60.dp,
    val spacing65: Dp = 65.dp,
    val spacing70: Dp = 70.dp,
    val spacing75: Dp = 75.dp,
    val spacing80: Dp = 80.dp,
    val spacing90: Dp = 90.dp,
    val spacing94: Dp = 94.dp,
    val spacing95: Dp = 95.dp,
    val spacing100: Dp = 100.dp,
    val spacing110: Dp = 110.dp,
    val spacing120: Dp = 120.dp,
    val spacing128: Dp = 128.dp,
    val spacing145: Dp = 145.dp,
    val spacing150: Dp = 150.dp,
    val spacing200: Dp = 200.dp,
    val spacing300: Dp = 300.dp,
    val spacing350: Dp = 350.dp,
    val spacing400: Dp = 400.dp,
    val spacing450: Dp = 450.dp
)

val LocalSpacing = compositionLocalOf { spacing() }

val MaterialTheme.Spacing: spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current

val Spacing: spacing
    get() = spacing()
