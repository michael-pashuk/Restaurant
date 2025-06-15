package com.michael.restaurant.data.database.booking.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Booking(
    @PrimaryKey
    @ColumnInfo(name = "timestamp")
    val timestamp: Long,
    @ColumnInfo(name = "guest")
    val guest: Int,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val phone: String,
    @ColumnInfo
    val email: String
)
