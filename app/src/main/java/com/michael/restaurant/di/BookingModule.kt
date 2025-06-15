package com.michael.restaurant.di

import android.content.Context
import androidx.room.Room
import com.michael.restaurant.data.database.booking.BookingDatabase
import com.michael.restaurant.data.database.booking.dao.BookingDao
import com.michael.restaurant.data.repository.BookingRepository
import com.michael.restaurant.service.helper.EmailHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BookingModule {

    @Singleton
    @Provides
    fun provideEmailHelper(): EmailHelper {
        return EmailHelper()
    }

    @Provides
    @Singleton
    fun provideBookingDatabase(
        @ApplicationContext context: Context
    ): BookingDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = BookingDatabase::class.java,
            name = "booking"
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideBookingDao(database: BookingDatabase): BookingDao {
        return database.bookingDao()
    }

    @Provides
    @Singleton
    fun provideBookingRepository(bookingDao: BookingDao): BookingRepository {
        return BookingRepository(bookingDao)
    }
}