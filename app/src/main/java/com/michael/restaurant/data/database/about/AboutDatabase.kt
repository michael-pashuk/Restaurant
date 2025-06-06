package com.michael.restaurant.data.database.about

import androidx.room.Database
import androidx.room.RoomDatabase
import com.michael.restaurant.data.database.about.dao.AboutDao
import com.michael.restaurant.data.database.about.model.About

@Database(entities = [About::class], version = 1, exportSchema = true)
abstract class AboutDatabase : RoomDatabase() {
    abstract fun aboutDao(): AboutDao
}