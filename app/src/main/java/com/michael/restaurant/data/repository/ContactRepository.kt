package com.michael.restaurant.data.repository

import com.michael.restaurant.data.database.contact.dao.MessageDao
import com.michael.restaurant.data.database.contact.model.Message
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactRepository
@Inject constructor(
    private val messageDao: MessageDao
) {

    suspend fun insert(message: Message) {
        messageDao.insert(message)
    }
}