package com.moengage.test.newsapp.homescreen.presentation.utils

import com.moengage.test.core.ui.base.UiEvents

/**
 * Sealed class representing different UI events specific to the home screen.
 */
sealed class HomeScreenUiEvents {
    /**
     * Event triggered to pass UI events to the ViewModel.
     * @param events The UI event to be passed.
     */
    class OnTriggerUiEvents(val events: UiEvents) : HomeScreenUiEvents()

    /**
     * Event indicating selection of recent articles sorting.
     */
    object OnRecentArticlesSelected : HomeScreenUiEvents()

    /**
     * Event indicating selection of old articles sorting.
     */
    object OnOldArticlesSelected : HomeScreenUiEvents()
}