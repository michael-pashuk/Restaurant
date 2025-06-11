package com.michael.restaurant.ui.screen.event

import com.michael.restaurant.data.database.event.model.Event

sealed class EventState {
    object Loading : EventState()
    data class Success(val events: List<Event>, val extendEvent: Event?) : EventState()
    object Empty : EventState()
    data class Error(val message: String?) : EventState()
}