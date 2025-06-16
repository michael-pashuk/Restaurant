package com.michael.restaurant.di

import android.content.Context
import androidx.room.Room
import com.michael.restaurant.data.database.contact.ContactDatabase
import com.michael.restaurant.data.database.contact.dao.MessageDao
import com.michael.restaurant.data.repository.ContactRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ContactModule {

    @Provides
    @Singleton
    fun provideContactDatabase(
        @ApplicationContext context: Context
    ): ContactDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = ContactDatabase::class.java,
            name = "contact"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMessageDao(database: ContactDatabase): MessageDao {
        return database.messageDao()
    }

    @Provides
    @Singleton
    fun provideContactRepository(messageDao: MessageDao): ContactRepository {
        return ContactRepository(messageDao)
    }
}