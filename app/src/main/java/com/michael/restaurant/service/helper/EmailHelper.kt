package com.michael.restaurant.service.helper

import android.content.Intent
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmailHelper @Inject constructor() {

    fun createIntent(
        email: String,
        date: Long,
        count: Int,
        name: String,
        phone: String
    ): Intent {
        val convertedDate = Instant
            .ofEpochMilli(date)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
            .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))

        val message = createMessage(email, convertedDate, count, name, phone)

        val theme = "Table reservation for $date"

        return Intent(Intent.ACTION_SEND).apply {
            type = "message/rfc822"
            putExtra(Intent.EXTRA_SUBJECT, theme)
            putExtra(Intent.EXTRA_TEXT, message)
        }
    }

    private fun createMessage(
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
}