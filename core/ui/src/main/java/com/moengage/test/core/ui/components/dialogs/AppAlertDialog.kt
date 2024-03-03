package com.moengage.test.core.ui.components.dialogs

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.DialogProperties
import com.moengage.test.core.ui.theme.Spacing


/**
 * Dialogs provide important prompts in a user flow. They can require an action, communicate
 * information, or help users accomplish a task.
 *
 * TODO Replace the current implementation with compose dialogs once the component is stable
 *
 * @param onDismissRequest called when the user tries to dismiss the Dialog by clicking outside
 * or pressing the back button. This is not called when the dismiss button is clicked.
 * @param modifier the [Modifier] to be applied to this dialog
 * @param properties typically platform specific properties to further configure the dialog.
 * @param content the content of the dialog
 */
@Composable
fun AppAlertDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    properties: DialogProperties = DialogProperties(),
    shape: Shape = MaterialTheme.shapes.large,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = .7f))
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = {},
            ),
    ) {
        Card(
            shape = shape,
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = with(modifier) {
                if (properties.usePlatformDefaultWidth) {
                    fillMaxWidth(.8f)
                } else this
            }.align(Alignment.Center),
        ) {
            Box(
                modifier = with(Modifier) {
                    if (properties.usePlatformDefaultWidth) {
                        this
                            .fillMaxWidth()
                            .padding(Spacing.spacing24)
                    } else this
                },
            ) {
                content()
            }
        }
    }

    BackHandler(!properties.dismissOnBackPress) {
        onDismissRequest()
    }
}

/**
 * Displays a common dialog pattern consisting of an illustration, title, description, and actions.
 *
 * @param onDismissRequest called when the user tries to dismiss the Dialog by clicking outside
 * or pressing the back button. This is not called when the dismiss button is clicked.
 * @param modifier the [Modifier] to be applied to this dialog
 */
@Composable
fun AppAlertDialog(
    illustration: Painter,
    title: @Composable () -> Unit,
    description: @Composable () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    actions: @Composable ColumnScope.() -> Unit,
) {
    AppAlertDialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
        modifier = modifier,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.Spacing.spacing16)
        ) {
            Image(
                painter = illustration,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(Spacing.spacing100)
            )

            CompositionLocalProvider(LocalTextStyle provides MaterialTheme.typography.displayMedium) {
                title()
            }

            CompositionLocalProvider(
                LocalTextStyle provides MaterialTheme.typography.titleMedium.copy(
                    textAlign = TextAlign.Center
                )
            ) {
                description()
            }

            actions()
        }
    }
}
