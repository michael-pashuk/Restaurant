package com.michael.restaurant.data.database.event.dao

import androidx.room.Dao
import androidx.room.Query
import com.michael.restaurant.data.database.event.model.Event

@Dao
interface EventDao {
    @Query("SELECT * FROM event")
    suspend fun getAll(): List<Event>

    @Query("SELECT * FROM event WHERE event_id = :id")
    suspend fun getEventById(id: Int): Event?

    @Query("SELECT * FROM event WHERE date >= strftime('%s', 'now') ORDER BY date ASC")
    suspend fun getFutureEvents(): List<Event>
}