package com.michael.restaurant.data.database.event

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.michael.restaurant.data.database.event.dao.EventDao
import com.michael.restaurant.data.database.event.model.Event
import com.michael.restaurant.service.converter.DateConverter

@Database(entities = [Event::class], version = 1, exportSchema = true)
@TypeConverters(DateConverter::class)
abstract class EventDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
}