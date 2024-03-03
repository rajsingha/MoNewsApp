package com.moengage.test.core.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.moengage.test.core.ui.components.textfields.UiString

/**
 * To map and observe Lifecycle events in Composable functions
 * */
@Composable
fun OnLifecycleEvent(onEvent: (owner: LifecycleOwner, event: Lifecycle.Event) -> Unit) {
    val eventHandler = rememberUpdatedState(onEvent)
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

    DisposableEffect(lifecycleOwner.value) {
        val lifecycle = lifecycleOwner.value.lifecycle
        val observer = LifecycleEventObserver { owner, event ->
            eventHandler.value(owner, event)
        }

        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
}

/**
 * Returns the string resource associated with the provided integer resource ID, or null if the integer is null.
 */
@Composable
fun Int?.getStringOrNull() = this?.let { stringResource(id = it) }

/**
 * Loads ui string if possible otherwise null
 */
@Composable
@ReadOnlyComposable
fun UiString?.getStringOrNull() = this?.let {
    com.moengage.test.core.ui.components.textfields.stringResource(it)
}