package com.michael.restaurant.data.database.menu.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["category_id"],
            childColumns = ["category_id"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class Dish(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "dish_id")
    val id: Int,
    val name: String,
    val price: Double,
    @ColumnInfo(name = "photo")
    val photoPath: String,
    @ColumnInfo(name = "on_main")
    val onMain: Boolean,
    @ColumnInfo(name = "category_id", index = true)
    val categoryId: Int?
)
