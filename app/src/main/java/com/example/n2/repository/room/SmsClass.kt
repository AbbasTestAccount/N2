package com.example.n2.repository.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("SmsTable")
data class SmsClass(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,

    val text : String,
    val sourceSim : Int,
    val recipients : Array<String>,
    var date : String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SmsClass

        if (text != other.text) return false
        if (sourceSim != other.sourceSim) return false
        if (!recipients.contentEquals(other.recipients)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = text.hashCode()
        result = 31 * result + sourceSim
        result = 31 * result + recipients.contentHashCode()
        return result
    }
}
