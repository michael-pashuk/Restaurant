package com.michael.restaurant.di

import android.content.Context
import androidx.room.Room
import com.michael.restaurant.data.database.menu.MenuDatabase
import com.michael.restaurant.data.database.menu.dao.MenuDao
import com.michael.restaurant.data.repository.MenuRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MenuModule {

    @Provides
    @Singleton
    fun provideMenuDatabase(
        @ApplicationContext context: Context
    ): MenuDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = MenuDatabase::class.java,
            name = "menu"
        )
            .createFromAsset("database/menu.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideMenuDao(database: MenuDatabase): MenuDao {
        return database.menuDao()
    }

    @Provides
    @Singleton
    fun provideMenuRepository(dao: MenuDao): MenuRepository {
        return MenuRepository(dao)
    }
}