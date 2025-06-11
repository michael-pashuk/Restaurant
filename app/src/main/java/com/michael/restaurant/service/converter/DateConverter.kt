package com.michael.restaurant.service.converter

import androidx.room.TypeConverter
import java.time.Instant
import java.util.Date

class DateConverter {

    @TypeConverter
    fun dateToTimestamp(date: Date): Long {
        return date.time.div(1000)
    }

    @TypeConverter
    fun timestampToDate(timestamp: Long): Date {
        var instant = Instant.ofEpochSecond(timestamp)
        return Date.from(instant)
    }
}