package com.michael.restaurant.data.database.contact.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Message(
    @PrimaryKey
    val timestamp: Long,
    val name: String,
    val email: String,
    val message: String
)
