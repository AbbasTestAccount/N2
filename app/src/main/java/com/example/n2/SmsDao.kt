package com.example.n2

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SmsDao {

    @Insert
    fun addSms(sms: SmsClass)


    @Query("SELECT * FROM SmsTable")
    fun getAllSms(): LiveData<List<SmsClass>>
}