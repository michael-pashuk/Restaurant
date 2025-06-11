package com.michael.restaurant.ui.screen.event

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michael.restaurant.data.database.event.model.Event
import com.michael.restaurant.data.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel
@Inject constructor(
    private val repository: EventRepository
) : ViewModel() {

    private val _eventState = MutableStateFlow<EventState>(EventState.Loading)
    val eventState: StateFlow<EventState> = _eventState.asStateFlow()

    init {
        clearEventId()
    }

    fun changeEventId(id: Int) {
        viewModelScope.launch {
            _eventState.value = EventState.Loading
            val event = repository.getEventById(id)

            if (event == null) {
                return@launch
            } else {
                _eventState.value = EventState.Success(listOf<Event>(), event)
            }
        }
    }

    fun clearEventId() {
        _eventState.value = EventState.Loading

        viewModelScope.launch {
            try {
                val events = repository.getFutureEvents()
                if (events.isEmpty()) {
                    _eventState.value = EventState.Empty
                } else {
                    _eventState.value = EventState.Success(events, null)
                }
            } catch (e: Exception) {
                Log.e("EVENT_VM", e.toString())
                _eventState.value = EventState.Error(e.toString())
            }
        }
    }
}