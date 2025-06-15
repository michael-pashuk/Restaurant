package com.michael.restaurant.data.repository

import com.michael.restaurant.data.database.event.dao.EventDao
import com.michael.restaurant.data.database.event.model.Event
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventRepository
@Inject constructor(
    private val eventDao: EventDao
) {
    suspend fun getEventById(id: Int): Event? {
        return eventDao.getEventById(id)
    }

    suspend fun getFutureEvents(): List<Event> {
        return eventDao.getFutureEvents()
    }
}