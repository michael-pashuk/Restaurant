package com.michael.restaurant.di

import android.content.Context
import androidx.room.Room
import com.michael.restaurant.data.database.event.EventDatabase
import com.michael.restaurant.data.database.event.dao.EventDao
import com.michael.restaurant.data.repository.EventRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EventModule {

    @Provides
    @Singleton
    fun provideEventDatabase(
        @ApplicationContext context: Context
    ): EventDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = EventDatabase::class.java,
            name = "event_database"
        )
            .createFromAsset("database/event.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideEventDao(database: EventDatabase): EventDao {
        return database.eventDao()
    }

    @Provides
    @Singleton
    fun provideEventRepository(eventDao: EventDao): EventRepository {
        return EventRepository(eventDao)
    }
}