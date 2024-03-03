package com.moengage.test.core.ui.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.DialogProperties
import com.moengage.test.core.ui.theme.Blue500
import com.moengage.test.core.ui.theme.Spacing


@Composable
fun ProgressDialog(
    modifier: Modifier = Modifier
) {
    AppAlertDialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        ),
        modifier = modifier.clip(MaterialTheme.shapes.large),
        shape = RoundedCornerShape(Spacing.spacing50)
    ) {
        Box(
            modifier = Modifier
                .background(Color.White)
                .padding(Spacing.spacing16),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.surfaceVariant,
                trackColor = Color.Black,
            )
        }
    }
}
