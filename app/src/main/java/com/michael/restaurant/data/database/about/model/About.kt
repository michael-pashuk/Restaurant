package com.michael.restaurant.data.database.about.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class About(
    @PrimaryKey
    @ColumnInfo(name = "language_code")
    val languageCode: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "body")
    val body: String,
    @ColumnInfo(name = "image")
    val imagePath: String
)