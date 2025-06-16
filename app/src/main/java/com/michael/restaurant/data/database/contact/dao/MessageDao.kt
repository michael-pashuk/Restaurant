package com.michael.restaurant.data.database.contact.dao

import androidx.room.Dao
import androidx.room.Insert
import com.michael.restaurant.data.database.contact.model.Message

@Dao
interface MessageDao {

    @Insert
    suspend fun insert(message: Message)
}