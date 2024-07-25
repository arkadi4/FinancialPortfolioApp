package com.example.financialportfolioapp.presentation.utils

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Calendar

object DateTimeUtils {
    val dateTimeFormatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")

    fun formatDateTime(dateTime: LocalDateTime, dateTimeFormatter: DateTimeFormatter): String {
        return dateTime.format(dateTimeFormatter)
    }

    fun parseDateTime(dateTimeString: String): LocalDateTime? {
        return try {
            LocalDateTime.parse(dateTimeString, dateTimeFormatter)
        } catch (e: DateTimeParseException) {
            null
        }
    }

    private fun calendarToLocalDateTime(calendar: Calendar): LocalDateTime {
        return calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
    }

    fun formatCalendar(calendar: Calendar, dateTimeFormatter: DateTimeFormatter): String {
        return formatDateTime(calendarToLocalDateTime(calendar), dateTimeFormatter)
    }
}
