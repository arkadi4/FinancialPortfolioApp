package com.example.financialportfolioapp.presentation.utils

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Calendar

object DateTimeUtils {
    private val dateTimeFormatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")

    private val currentDateTime = LocalDateTime.now()
    fun formatDateTime(dateTime: LocalDateTime): String {
        return dateTime.format(dateTimeFormatter)
    }

    fun parseDateTime(dateTimeString: String): LocalDateTime? {
        return try {
            LocalDateTime.parse(dateTimeString, dateTimeFormatter)
        } catch (e: DateTimeParseException) {
            null
        }
    }

    fun getCurrentDateTime(): LocalDateTime {
        return currentDateTime
    }

    fun calendarToLocalDateTime(calendar: Calendar): LocalDateTime {
        return calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
    }

    fun formatCalendar(calendar: Calendar): String {
        return formatDateTime(calendarToLocalDateTime(calendar))
    }
}
