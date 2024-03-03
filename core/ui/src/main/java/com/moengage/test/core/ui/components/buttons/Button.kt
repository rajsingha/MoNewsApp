package com.moengage.test.core.ui.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * This is a generic button, with a call back for onclick,
modifier, padding, shape, border, colors, enabled, backgroundColor,
contentColor and content can be customized.
usage :
Button(onClick = {
onclick callback
},shape = CircleShape){
Image(painterResource(R.drawable.stop_button), "content description")
Text(text = "Button With text")
}
 * */
@Composable
fun Button(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    internalModifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    border: BorderStroke? = null,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    elevation: ButtonElevation? = null,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    debounceDuration: Long = 800L,
    content: @Composable RowScope.() -> Unit,
) {
    var clickInProgress by remember { mutableStateOf(false) }
    var job: Job? by remember { mutableStateOf(null) }

    Button(
        onClick = {
            if (clickInProgress.not()) {
                clickInProgress = true
                job?.cancel() // Cancel the previous job if it exists
                job = coroutineScope.launch {
                    delay(debounceDuration)
                    clickInProgress = false
                }
                onClick.invoke()
            }
        },
        shape = shape,
        border = border,
        elevation = elevation,
        colors = colors,
        modifier = modifier,
        enabled = enabled && clickInProgress.not()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = internalModifier,
            content = content
        )
    }
}
