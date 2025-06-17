package com.michael.restaurant.service.converter

import androidx.room.TypeConverter

class BooleanConverter {

    @TypeConverter
    fun boolToInt(value: Boolean): Int {
        return if (value) 1 else 0
    }

    @TypeConverter
    fun intToBool(value: Int): Boolean {
        return value != 0
    }
}