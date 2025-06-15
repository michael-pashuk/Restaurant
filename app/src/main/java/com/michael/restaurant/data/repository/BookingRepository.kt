package com.michael.restaurant.data.repository

import com.michael.restaurant.data.database.booking.dao.BookingDao
import com.michael.restaurant.data.database.booking.model.Booking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookingRepository
@Inject constructor(
    private val bookingDao: BookingDao
) {
    suspend fun insert(booking: Booking) {
        bookingDao.insert(booking)
    }
}