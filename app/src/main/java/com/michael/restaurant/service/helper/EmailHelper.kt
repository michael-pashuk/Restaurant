package com.michael.restaurant.service.helper

import android.content.Intent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmailHelper @Inject constructor() {

    fun createIntent(
        theme: String,
        message: String
    ): Intent = Intent(Intent.ACTION_SEND).apply {
        type = "message/rfc822"
        putExtra(Intent.EXTRA_SUBJECT, theme)
        putExtra(Intent.EXTRA_TEXT, message)
    }

    fun createBookingMessage(
        email: String,
        date: String,
        count: Int,
        name: String,
        phone: String
    ): String {

        return """
            Hello!

            I would like to reserve a table for $date for $count guests.

            My details:
            Name: $name
            Phone: +7$phone
            Email: $email

            Please confirm the reservation or clarify the details. Thank you!
        """.trimIndent()
    }

    fun createContactMessage(
        email: String,
        name: String,
        message: String
    ): String {
        return """
            $message
            
            My details:
            Name: $name
            Email: $email
        """.trimIndent()
    }
}