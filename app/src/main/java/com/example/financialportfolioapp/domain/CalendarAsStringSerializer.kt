package com.example.financialportfolioapp.domain

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object CalendarAsStringSerializer : KSerializer<Calendar> {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("Calendar", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Calendar) {
        val dateString = dateFormat.format(value.time)
        encoder.encodeString(dateString)
    }

    override fun deserialize(decoder: Decoder): Calendar {
        val dateString = decoder.decodeString()
        return Calendar.getInstance().apply {
            time = dateFormat.parse(dateString)
                ?: throw IllegalArgumentException("Invalid date format")
        }
    }
}
