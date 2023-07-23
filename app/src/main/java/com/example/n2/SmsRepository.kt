package com.example.n2

interface SmsRepository {
    fun addItem(smsItem : SmsClass)
    fun removeItem(smsItem : SmsClass)

    fun getItems() : ArrayList<SmsClass>
}