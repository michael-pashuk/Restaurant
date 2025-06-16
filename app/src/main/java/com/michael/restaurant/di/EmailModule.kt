package com.michael.restaurant.di

import com.michael.restaurant.service.helper.EmailHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EmailModule {

    @Provides
    @Singleton
    fun provideEmailHelper(): EmailHelper {
        return EmailHelper()
    }
}