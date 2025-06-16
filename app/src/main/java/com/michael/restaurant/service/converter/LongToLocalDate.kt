package com.michael.restaurant.service.converter

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

fun longToLocalDate(date: Long): LocalDate {
    return Instant
        .ofEpochMilli(date)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
}