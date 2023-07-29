package com.example.n2

import androidx.room.TypeConverter
import com.google.gson.Gson

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