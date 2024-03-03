package com.moengage.test.core.ui.components.textfields

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

/**
 * UI String abstraction created from the viewmodel layer
 */
sealed class UiString {
    data class ResourceString(@StringRes val resId: Int?) : UiString()
    data class DynamicString(val value: String?) : UiString()
}

/**
 * Loads the ui string.
 *
 * @param uiString ui string wrapper
 * @return the string data associated with the wrapper
 */
@Composable
@ReadOnlyComposable
fun stringResource(uiString: UiString): String? {
    return when (uiString) {
        is UiString.DynamicString -> uiString.value
        is UiString.ResourceString -> uiString.resId?.let {
            androidx.compose.ui.res.stringResource(id = it)
        }
    }
}
