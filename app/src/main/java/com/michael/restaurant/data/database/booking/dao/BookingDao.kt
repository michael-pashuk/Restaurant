package com.michael.restaurant.data.database.booking.dao

import androidx.room.Dao
import androidx.room.Insert
import com.michael.restaurant.data.database.booking.model.Booking

@Dao
interface BookingDao {
    @Insert
    suspend fun insert(booking: Booking)
}