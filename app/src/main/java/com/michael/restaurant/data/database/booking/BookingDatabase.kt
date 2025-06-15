package com.michael.restaurant.data.database.booking

import androidx.room.Database
import androidx.room.RoomDatabase
import com.michael.restaurant.data.database.booking.dao.BookingDao
import com.michael.restaurant.data.database.booking.model.Booking

@Database(entities = [Booking::class], version = 1)
abstract class BookingDatabase : RoomDatabase() {
    abstract fun bookingDao(): BookingDao
}