package com.michael.restaurant.data.database.menu.dao

import androidx.room.Dao
import androidx.room.Query
import com.michael.restaurant.data.database.menu.model.Category
import com.michael.restaurant.data.database.menu.model.Dish

@Dao
interface MenuDao {
    @Query("SELECT * FROM dish")
    suspend fun getAllDish(): List<Dish>

    @Query("SELECT * FROM category")
    suspend fun getAllCategory(): List<Category>

    @Query("SELECT * FROM dish WHERE on_main = 1 LIMIT :quantity")
    suspend fun getOnMainInQuantity(quantity: Int): List<Dish>

    @Query("SELECT * FROM dish WHERE dish.category_id = :categoryId")
    suspend fun getDishesByCategoryOnMain(categoryId: Int): List<Dish>
}