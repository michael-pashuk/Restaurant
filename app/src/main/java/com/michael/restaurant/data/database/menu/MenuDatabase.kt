package com.michael.restaurant.data.database.menu

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.michael.restaurant.data.database.menu.dao.MenuDao
import com.michael.restaurant.data.database.menu.model.Category
import com.michael.restaurant.data.database.menu.model.Dish
import com.michael.restaurant.service.converter.BooleanConverter

@Database(entities = [Category::class, Dish::class], version = 1, exportSchema = true)
@TypeConverters(BooleanConverter::class)
abstract class MenuDatabase: RoomDatabase() {
    abstract fun menuDao(): MenuDao
}