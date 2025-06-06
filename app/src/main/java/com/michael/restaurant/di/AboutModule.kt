package com.michael.restaurant.di

import android.content.Context
import androidx.room.Room
import com.michael.restaurant.data.database.about.AboutDatabase
import com.michael.restaurant.data.database.about.dao.AboutDao
import com.michael.restaurant.data.repository.AboutRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AboutModule {

    @Provides
    @Singleton
    fun providerAboutDatabase(
        @ApplicationContext context: Context
    ): AboutDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = AboutDatabase::class.java,
            name = "about_database"
        )
            .createFromAsset("database/about.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideAboutDao(database: AboutDatabase): AboutDao {
        return database.aboutDao()
    }

    @Provides
    @Singleton
    fun provideAboutRepository(dao: AboutDao): AboutRepository {
        return AboutRepository(dao)
    }
}