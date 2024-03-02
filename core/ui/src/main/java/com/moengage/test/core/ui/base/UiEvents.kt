package com.moengage.test.core.ui.base

import android.os.Bundle

/**
 * [UiEvents] class to pass UI Event details
 * */
sealed class UiEvents {

    /**
     * [Navigate] class for Navigation
     * */
    data class Navigate(val route: String, val bundle: Bundle? = null) : UiEvents()

    /**
     * [ShowSnackBar] class for showing SnackBar
     * */
    data class ShowSnackBar(val message: String) : UiEvents()

    /**
     * [ShowToast] class for showing Toast
     * */
    data class ShowToast(val message: String) : UiEvents()
}
