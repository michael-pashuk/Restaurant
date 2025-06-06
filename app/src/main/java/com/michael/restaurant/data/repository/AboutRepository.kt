package com.michael.restaurant.data.repository

import com.michael.restaurant.data.database.about.dao.AboutDao
import com.michael.restaurant.data.database.about.model.About
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AboutRepository @Inject constructor(private val aboutDao: AboutDao) {

    suspend fun getAboutByLanguageCode(code: String): About? {
        return aboutDao.getAboutByLanguageCode(code)
    }

    fun update(about: About) {
        aboutDao.update(about)
    }

    fun delete(about: About) {
        aboutDao.delete(about)
    }

    fun insert(about: About) {
        aboutDao.insert(about)
    }
}