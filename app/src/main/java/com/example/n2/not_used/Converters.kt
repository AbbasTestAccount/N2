package com.example.n2.not_used

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromString(value: String?): Array<String>? {
        return value?.split(",")?.toTypedArray()
    }

    @TypeConverter
    fun toString(value: Array<String>?): String? {
        return value?.joinToString(",")
    }
}