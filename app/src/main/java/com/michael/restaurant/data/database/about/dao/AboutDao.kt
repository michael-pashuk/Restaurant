package com.michael.restaurant.data.database.about.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.michael.restaurant.data.database.about.model.About

@Dao
interface AboutDao {
    @Query("SELECT * FROM about WHERE language_code = :code LIMIT 1")
    suspend fun getAboutByLanguageCode(code: String): About?

    @Update
    fun update(about: About)

    @Insert
    fun insert(about: About)

    @Delete
    fun delete(about: About)
}