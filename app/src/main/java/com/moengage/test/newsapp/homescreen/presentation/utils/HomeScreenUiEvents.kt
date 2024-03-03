package com.moengage.test.newsapp.homescreen.presentation.utils

import com.moengage.test.core.ui.base.UiEvents

sealed class HomeScreenUiEvents {
    class OnTriggerUiEvents(val events: UiEvents): HomeScreenUiEvents()

    object OnRecentArticlesSelected : HomeScreenUiEvents()

    object OnOldArticlesSelected: HomeScreenUiEvents()

}