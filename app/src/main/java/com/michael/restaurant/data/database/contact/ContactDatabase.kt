package com.michael.restaurant.data.database.contact

import androidx.room.Database
import androidx.room.RoomDatabase
import com.michael.restaurant.data.database.contact.dao.MessageDao
import com.michael.restaurant.data.database.contact.model.Message

@Database(entities = [Message::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
}