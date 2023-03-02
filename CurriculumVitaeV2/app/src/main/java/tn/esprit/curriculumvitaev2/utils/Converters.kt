package tn.esprit.curriculumvitaev2.utils

import androidx.room.TypeConverter
import java.sql.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: String?): Date? {
        return value?.let { Date.valueOf(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): String? {
        return date?.toString()
    }
}
