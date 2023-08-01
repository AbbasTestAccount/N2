package com.example.n2

interface SmsRepository {
    fun addItem(smsItem : SmsClass) : SmsClass
    fun removeItem(smsItem : SmsClass) : SmsClass

    fun getItems() : ArrayList<SmsClass>
}