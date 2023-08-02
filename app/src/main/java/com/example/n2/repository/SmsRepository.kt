package com.example.n2.repository

import com.example.n2.room.SmsClass

interface SmsRepository {
    fun addItem(smsItem : SmsClass) : SmsClass
    fun removeItem(smsItem : SmsClass) : SmsClass

    fun getItems() : ArrayList<SmsClass>
}