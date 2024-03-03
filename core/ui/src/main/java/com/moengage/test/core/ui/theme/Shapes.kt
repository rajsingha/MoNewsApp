package com.moengage.test.core.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val shapes = Shapes(
    extraSmall = RoundedCornerShape(Spacing.default),
    small = RoundedCornerShape(Spacing.spacing4),
    medium = RoundedCornerShape(Spacing.spacing8),
    large = RoundedCornerShape(Spacing.spacing12)
)