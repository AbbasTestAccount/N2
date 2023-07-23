package com.example.n2

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SmsService2 : KoinComponent {

    private val smsRepository by inject<SmsRepository>()

    fun smsList(): List<SmsClass> = smsRepository.getItems()

    fun addSms(smsClass: SmsClass) {
        smsRepository.addItem(smsClass)
    }

    fun removeSms(smsClass: SmsClass) {
        smsRepository.removeItem(smsClass)
    }
}
