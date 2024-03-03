package com.moengage.test.core.ui.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import com.moengage.test.core.ui.theme.Spacing


/**
 * This is a Primary button, with a call back for onclick,
modifier, padding, shape, border, enabled
and content can be customized.
usage :
PrimaryButton(onClick = {onclick callback})
}**/
@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    shape: Shape = MaterialTheme.shapes.large,
    border: BorderStroke? = null,
    enabled: Boolean = true,
    textModifier: Modifier = Modifier.padding(vertical = Spacing.spacing4),
    text: String,
    textColor: Color = Color.Unspecified,
    textStyle: TextStyle = MaterialTheme.typography.titleSmall
) {
    Button(
        onClick = onClick,
        shape = shape,
        border = border,
        elevation = null,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.Black,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.Black
        ),
        modifier = modifier,
        enabled = enabled
    ) {
        Text(
            text = text,
            style = textStyle,
            modifier = textModifier,
            color = textColor
        )
    }
}
