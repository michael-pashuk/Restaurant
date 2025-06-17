package com.michael.restaurant.data.repository

import com.michael.restaurant.data.database.menu.dao.MenuDao
import com.michael.restaurant.data.database.menu.model.Category
import com.michael.restaurant.data.database.menu.model.Dish
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MenuRepository
@Inject constructor(
    private val menuDao: MenuDao
) {

    suspend fun getAllDish(): List<Dish> {
        return menuDao.getAllDish()
    }

    suspend fun getAllCategory(): List<Category> {
        return menuDao.getAllCategory()
    }

    suspend fun getOnMainInQuantity(quantity: Int): List<Dish> {
        return menuDao.getOnMainInQuantity(quantity)
    }

    suspend fun getDishesByCategoryOnMain(categoryId: Int): List<Dish> {
        return menuDao.getDishesByCategoryOnMain(categoryId)
    }
}